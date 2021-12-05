package com.online_exams.university_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Department;
import com.online_exams.university_project.entities.Speciality;
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long>{
	List<Speciality> findByDepartement(Department departement);
}
