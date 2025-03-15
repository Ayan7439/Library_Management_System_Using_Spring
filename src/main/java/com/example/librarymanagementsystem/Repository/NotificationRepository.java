package com.example.librarymanagementsystem.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementsystem.entities.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
