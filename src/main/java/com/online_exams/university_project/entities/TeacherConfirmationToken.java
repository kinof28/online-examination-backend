package com.online_exams.university_project.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.online_exams.university_project.base_entities.BaseConfirmationToken;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TeacherConfirmationToken extends BaseConfirmationToken{
	@ManyToOne
    @JoinColumn(nullable = false )
	private Teacher teacher;
	
	public TeacherConfirmationToken(String token,Teacher teacher) {
		super(token);
		this.teacher = teacher;
	}
	
}
