package com.online_exams.university_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Degree;
@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long>{

}
