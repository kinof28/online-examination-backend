package com.online_exams.university_project;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.online_exams.university_project.services.AdminServices;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class OnlineExamsApplication {
	
	private AdminServices adminService;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineExamsApplication.class, args);
	}
	
	@PostConstruct
	public void initializeAdmin() {
		this.adminService.initializeAdmin("admin@admin.com","000000");
	}

}
