package com.online_exams.university_project.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
//	private Option option;
	private Date startDate;
	private Date endDate;
//	private Teacher teacher;
}
