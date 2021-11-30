package com.online_exams.university_project.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.online_exams.university_project.base_entities.BaseEntity;
import com.online_exams.university_project.enums.Level;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Option extends BaseEntity{
	@Enumerated(EnumType.STRING)
	private Level level;
	@ManyToOne
	private Speciality speciality;
	@Override
	public String toString() {
		return "level:" + level + ", speciality :" + speciality.getName() + ", name:" + getName()
				+ ", Description:" + getDescription();
	}
	
}
