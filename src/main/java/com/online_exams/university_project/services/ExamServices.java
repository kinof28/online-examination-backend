package com.online_exams.university_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online_exams.university_project.dtos.ExamDTO;
import com.online_exams.university_project.entities.Exam;
import com.online_exams.university_project.mappers.ExamMapper;
import com.online_exams.university_project.repositories.ExamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExamServices {
	
	private ExamRepository repository;
	private ExamMapper mapper;
	
	public List<ExamDTO> getAllExams(){
		return this.mapper.getAllDTOs(this.repository.findAll());
	}
	public boolean deleteExam(long id) {
		Optional<Exam> examO=this.repository.findById(id);
		if(examO.isPresent()) {
			this.repository.delete(examO.get());
			return true;
		}return false;
	}

}
