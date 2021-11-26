package com.online_exams.university_project.mappers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.online_exams.university_project.dtos.ExamDTO;
import com.online_exams.university_project.entities.Exam;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExamMapper implements Mapper<Exam, ExamDTO> {
	private TeacherMapper teacherMapper;
	@Override
	public ExamDTO getOneDTO(Exam exam) {
		return new ExamDTO(
				exam.getId(),
				exam.getOption().toString(),
				exam.getStartDate(),
				exam.getEndDate(),
				this.teacherMapper.getOneDTO(exam.getTeacher()));
	}

	@Override
	public List<ExamDTO> getAllDTOs(List<Exam> exams) {
		List<ExamDTO> examsDTOs = new LinkedList<>();
		for(Exam exam:exams) {
			examsDTOs.add(this.getOneDTO(exam));
		}
		return examsDTOs;
	}

}
