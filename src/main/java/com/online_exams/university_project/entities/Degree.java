package com.online_exams.university_project.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.online_exams.university_project.base_entities.BaseEntity;
import com.online_exams.university_project.enums.DegreeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Degree extends BaseEntity{
	@Enumerated(EnumType.STRING)
	private DegreeType type;
//	private List<Option> options;
}
