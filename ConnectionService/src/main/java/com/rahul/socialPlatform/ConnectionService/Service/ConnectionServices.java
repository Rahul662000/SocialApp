package com.rahul.socialPlatform.ConnectionService.Service;

import com.rahul.socialPlatform.ConnectionService.Auth.UserContextHolder;
import com.rahul.socialPlatform.ConnectionService.Entity.Person;
import com.rahul.socialPlatform.ConnectionService.Events.AcceptConnectionRequestEvent;
import com.rahul.socialPlatform.ConnectionService.Events.SendConnectionRequestEvent;
import com.rahul.socialPlatform.ConnectionService.Repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServices {

    private final PersonRepository personRepository;
    private final KafkaTemplate<Long , SendConnectionRequestEvent> sendConnectionRequestEventKafkaTemplate;
    private final KafkaTemplate<Long , AcceptConnectionRequestEvent> acceptConnectionRequestEventKafkaTemplate;

    public List<Person> getFirstDegreeConnections(){

        Long userId = UserContextHolder.getCurrentUserId();

        log.info("Getting first degree connections for userId : {}" , userId);

        return personRepository.getFirstDegreeConnections(userId);
    }

    public Boolean sendConnectionRequest(Long receiverId) {

        Long senderId = UserContextHolder.getCurrentUserId();

        log.info("Trying to send connection Request, Sender: {} , receiver: {} " , senderId , receiverId);

        boolean alreadySendRequest = personRepository.connectionRequestExists(senderId , receiverId);
        if(alreadySendRequest){
            throw new RuntimeException("Connection Request Already Exists , cannot send new Request");
        }

        if(senderId.equals(receiverId)){
            throw new RuntimeException("Both Sender And Reciever are the Same");
        }

        boolean alreadyConnected = personRepository.alreadyConnected(senderId , receiverId);
        if (alreadyConnected){
            throw new RuntimeException("Connection Already Exists , cannot send new Request");
        }

        log.info("Successfully sent the connection Request");

        personRepository.addConnectionRequest(senderId , receiverId);

        SendConnectionRequestEvent sendConnectionRequestEvent = SendConnectionRequestEvent
                    .builder()
                    .receiverId(receiverId)
                    .senderId(senderId)
                    .build();

        sendConnectionRequestEventKafkaTemplate.send("send-connection-request-topic" , sendConnectionRequestEvent);

        return true;
    }

    public Boolean acceptConnectionRequest(Long senderId) {

        Long userId = UserContextHolder.getCurrentUserId();

        boolean connectionRequestExists = personRepository.connectionRequestExists(senderId , userId);

        if (!connectionRequestExists){
            throw new RuntimeException("No Connection Request Exists to Accept");
        }

        personRepository.acceptConnectionRequest(senderId , userId);

        log.info("Successfully accepted the connection request, sender: {} , receiver: {} " , senderId , userId);

        AcceptConnectionRequestEvent acceptConnectionRequestEvent = AcceptConnectionRequestEvent
                .builder()
                .receiverId(userId)
                .senderId(senderId)
                .build();

        acceptConnectionRequestEventKafkaTemplate.send("accept-connection-request-topic" , acceptConnectionRequestEvent);
        return true;
    }

    public Boolean rejectConnectionRequest(Long senderId) {

        Long receiverId = UserContextHolder.getCurrentUserId();

        boolean connectionRequestsExists = personRepository.connectionRequestExists(senderId,receiverId);
        if(!connectionRequestsExists){
            throw new RuntimeException("No Connection Request exists");
        }

        personRepository.rejectConnectionRequest(senderId , receiverId);

        return true;

    }
}
