package com.online_exams.university_project.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online_exams.university_project.base_entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Speciality extends BaseEntity {
	
	@ManyToOne
	@JsonIgnore
	private Department departement;

}
