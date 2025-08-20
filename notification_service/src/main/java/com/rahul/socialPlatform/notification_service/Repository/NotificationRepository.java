package com.rahul.socialPlatform.notification_service.Repository;

import com.rahul.socialPlatform.notification_service.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification , Long> {
}
