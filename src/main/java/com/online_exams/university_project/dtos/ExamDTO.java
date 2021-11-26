package com.online_exams.university_project.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ExamDTO {
	private long id;
	private String option;
	private Date startDate;
	private Date endDate;
	private TeacherDTO teacher;
}
