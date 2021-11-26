package com.online_exams.university_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online_exams.university_project.entities.HelpMessage;

public interface HelpMessageRepository extends JpaRepository<HelpMessage, Long>{

}
