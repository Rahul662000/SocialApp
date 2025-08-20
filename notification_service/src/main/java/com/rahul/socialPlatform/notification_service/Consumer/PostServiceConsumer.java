package com.rahul.socialPlatform.notification_service.Consumer;

import com.rahul.socialPlatform.notification_service.Clients.ConnectionClients;
import com.rahul.socialPlatform.notification_service.Dto.PersonDto;
import com.rahul.socialPlatform.notification_service.Entity.Notification;
import com.rahul.socialPlatform.notification_service.Repository.NotificationRepository;
import com.rahul.socialPlatform.post_service.Events.PostCreatedEvents;
import com.rahul.socialPlatform.post_service.Events.PostLikedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceConsumer {

    private final ConnectionClients connectionClients;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "post-created-topic")
    public void handlePostCreated(PostCreatedEvents postCreatedEvents){

        log.info("Sending Notifications: handlePostCreated : {}" , postCreatedEvents);

        List<PersonDto> connections = connectionClients.getFirstConnections(postCreatedEvents.getCreatorId());

        for(PersonDto connection : connections){
            sendNotification(connection.getUserId() , "Your Connection " + postCreatedEvents.getCreatorId()+" has created a new Post , checkOut");
        }

    }

    @KafkaListener(topics = "post-liked-topic")
    public void handlePostLiked(PostLikedEvent postLikedEvent){

        log.info("Sending Notifications: handlePostLiked : {}" , postLikedEvent);

        String message = String.format("Your Post, %d has been liked by %d" , postLikedEvent.getPostId() , postLikedEvent.getLikedByUserId());

        sendNotification(postLikedEvent.getCreatorId() , message);



    }

    public void sendNotification(Long userId , String message){

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        notificationRepository.save(notification);

    }


}
