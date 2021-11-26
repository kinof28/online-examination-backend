package com.online_exams.university_project.base_entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.online_exams.university_project.entities.Department;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseClient extends BaseUser {
	
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	@OneToOne
	private Department department;
	

}
