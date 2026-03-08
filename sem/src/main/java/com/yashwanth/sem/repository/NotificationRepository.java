package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.Notification;
import com.yashwanth.sem.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiver(User user);

    List<Notification> findByReadStatus(boolean readStatus);

    List<Notification> findByReceiverAndReadStatus(User user, boolean readStatus);

}