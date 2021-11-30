package com.online_exams.university_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	Optional<Teacher>findByEmail(String email);

	List<Teacher> findAllByIsActivatedIsTrueAndIsLockedIsFalse();

}
