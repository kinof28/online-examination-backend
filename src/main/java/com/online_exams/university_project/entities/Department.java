package com.online_exams.university_project.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.online_exams.university_project.base_entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Department extends BaseEntity {
//	private Faculty faculty;
	@OneToMany
	private List<Degree> degrees;
}