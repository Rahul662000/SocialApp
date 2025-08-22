package com.rahul.socialPlatform.notification_service.Service;

import com.rahul.socialPlatform.notification_service.Entity.Notification;
import com.rahul.socialPlatform.notification_service.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotification {

    private final NotificationRepository notificationRepository;

    public void send(Long userId , String message){

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        notificationRepository.save(notification);

        log.info("Notification Saved for user: {}" , userId);

    }

}
