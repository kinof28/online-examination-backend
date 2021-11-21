package com.online_exams.university_project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/admin")
@CrossOrigin(origins = "localhost:4200")
public class AdminController {
	@GetMapping("/")
	private String test() {
		return "hello world From Admin Controller !";
	}
}
