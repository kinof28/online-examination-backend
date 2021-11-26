package com.online_exams.university_project.entities;

import javax.persistence.Entity;

import com.online_exams.university_project.base_entities.BaseConfirmationToken;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentConfirmationToken extends BaseConfirmationToken {
	private Student student;

	public StudentConfirmationToken(String token, Student student) {
		super(token);
		this.student = student;
	}
	
}
