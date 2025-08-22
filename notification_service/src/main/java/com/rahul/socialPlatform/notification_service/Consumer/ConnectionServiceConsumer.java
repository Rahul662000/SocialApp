package com.rahul.socialPlatform.notification_service.Consumer;

import com.rahul.socialPlatform.ConnectionService.Events.AcceptConnectionRequestEvent;
import com.rahul.socialPlatform.ConnectionService.Events.SendConnectionRequestEvent;
import com.rahul.socialPlatform.notification_service.Service.SendNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceConsumer {

    private final SendNotification sendNotification;

    @KafkaListener(topics = "send-connection-request-topic")
    public void handleSendConnectionRequest(SendConnectionRequestEvent sendConnectionRequestEvent){

        log.info("Handle Connections : handleSendConnectionRequest : {}" , sendConnectionRequestEvent);

        String message = "You have received a message from user with id: %d"+sendConnectionRequestEvent.getSenderId() ;

        sendNotification.send(sendConnectionRequestEvent.getReceiverId(),message);

    }

    @KafkaListener(topics = "accept-connection-request-topic")
    public void handleAcceptConnectionRequest(AcceptConnectionRequestEvent acceptConnectionRequestEvent){

        log.info("Handle Connections : handleAcceptConnectionRequest : {}" , acceptConnectionRequestEvent);

        String message = "Your connection request accepted by: %d"+acceptConnectionRequestEvent.getReceiverId();

        sendNotification.send(acceptConnectionRequestEvent.getSenderId(),message);

    }

}
