package com.online_exams.university_project.controllers;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_exams.university_project.entities.HelpMessage;
import com.online_exams.university_project.requests.LoginRequest;
import com.online_exams.university_project.requests.StudentSubscribeRequest;
import com.online_exams.university_project.requests.TeacherSubscribeRequest;
import com.online_exams.university_project.services.HelpService;
import com.online_exams.university_project.services.StudentServices;
import com.online_exams.university_project.services.TeacherServices;
import com.online_exams.university_project.utilities.JWTUtility;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/public")
@CrossOrigin(origins = "localhost:4200")
public class PublicController {
	
	private JWTUtility jwtUtility;
	private AuthenticationManager authenticationManager;
	private StudentServices studentService;
	private TeacherServices teacherService;
	private HelpService helpService;
	
	
	@GetMapping("/")
	private String test() {
		return "hello world From Public Controller !";
	}
	@GetMapping("/student/lost-password/{email}")
	private String studentPasswordRecovery(@PathVariable String email) {
		return "password recovered";
	}
	@GetMapping("/teacher/lost-password/{email}")
	private String teacherPasswordRecovery(@PathVariable String email) {
		return "password recovered";
	}
	@GetMapping(value="/student/confirm/{token}")
    private String confirmStudentEmail(@PathVariable String token){
        return this.studentService.activateAccount(token);
    }
	@GetMapping(value="/teacher/confirm/{token}")
    private String confirmTeacherEmail(@PathVariable String token){
        return this.teacherService.activateAccount(token);
    }
	
	
	@PostMapping("/staouti-il-m1-admin")
	private String adminLogin(@RequestBody LoginRequest request) {
		try{

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(("admin_"+request.getUserName()),request.getPassword()));

        }catch(AuthenticationException e ){
            return "\" bad Credentials \"";
        }
        return "\"Bearer "+this.jwtUtility.generateToken(request.getUserName())+"\"";
     }
	@PostMapping("/student/login")
	private String studentLogin(@RequestBody LoginRequest request) {
		try{

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        }catch(AuthenticationException e ){
            return "\" bad Credentials \"";
        }
        return "\"Bearer "+this.jwtUtility.generateToken(request.getUserName())+"\"";
     }
	@PostMapping("/teacher/login")
	private String teacherLogin(@RequestBody LoginRequest request) {
		try{

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(("teacher_"+request.getUserName()),request.getPassword()));

        }catch(AuthenticationException e ){
            return "\" bad Credentials \"";
        }
        return "\"Bearer "+this.jwtUtility.generateToken(request.getUserName())+"\"";
     }
	
	
	
	
	@PostMapping("/student/subscribe")
	private String studentSubscribe(@RequestBody StudentSubscribeRequest request) {
		
		return "subscribed";
	}
	@PostMapping("/teacher/subscribe")
	private String teacherSubscribe (@RequestBody TeacherSubscribeRequest request) {
		return "subscribed";
	}
	@PostMapping(value="/help")
    private String SubmitHelpRequest(@RequestBody HelpMessage helpMessage){
            this.helpService.submitHelpRequest(helpMessage);
            return "\"submiteded ....\"";
    }




}
