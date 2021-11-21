package com.online_exams.university_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
