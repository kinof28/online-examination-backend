package com.online_exams.university_project.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.online_exams.university_project.enums.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Enumerated(EnumType.STRING)
	private NotificationType type;
	private boolean isActive;
}
