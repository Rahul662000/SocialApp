package com.rahul.socialPlatform.notification_service.Consumer;

import com.rahul.socialPlatform.notification_service.Auth.UserContextHolder;
import com.rahul.socialPlatform.notification_service.Clients.ConnectionClients;
import com.rahul.socialPlatform.notification_service.Dto.PersonDto;
import com.rahul.socialPlatform.posts_service.Events.PostCreatedEvents;
import com.rahul.socialPlatform.posts_service.Events.PostLikedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.rahul.socialPlatform.notification_service.Service.SendNotification;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceConsumer {

    private final ConnectionClients connectionClients;
    private final SendNotification sendNotification;

    @KafkaListener(topics = "post-created-topic")
    public void handlePostCreated(PostCreatedEvents postCreatedEvents){

        log.info("Sending Notifications: handlePostCreated : {}" , postCreatedEvents);

        Long userId = UserContextHolder.getCurrentUserId();

        System.out.println("User Id : " + userId + "Crateor Id : " + postCreatedEvents.getCreatorId());

        List<PersonDto> connections = connectionClients.getFirstConnections();

        for(PersonDto connection : connections){
            sendNotification.send(connection.getUserId() , "Your Connection " + postCreatedEvents.getCreatorId()+" has created a new Post , checkOut");
        }

    }

    @KafkaListener(topics = "post-liked-topic")
    public void handlePostLiked(PostLikedEvent postLikedEvent){

        log.info("Sending Notifications: handlePostLiked : {}" , postLikedEvent);

        String message = String.format("Your Post, %d has been liked by %d" , postLikedEvent.getPostId() , postLikedEvent.getLikedByUserId());

        sendNotification.send(postLikedEvent.getCreatorId() , message);

    }




}
