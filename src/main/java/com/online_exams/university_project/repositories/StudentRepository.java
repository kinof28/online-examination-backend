package com.online_exams.university_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	Optional<Student> findByEmail(String email);

}
