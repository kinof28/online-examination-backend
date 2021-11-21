package com.online_exams.university_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_exams.university_project.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByEmail(String email);

}
