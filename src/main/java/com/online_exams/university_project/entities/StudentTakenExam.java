package com.online_exams.university_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class StudentTakenExam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
//	private Exam exam;
//	private Student student;
	private float mark;
}
