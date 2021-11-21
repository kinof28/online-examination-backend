package com.online_exams.university_project.controllers;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_exams.university_project.requests.LoginRequest;
import com.online_exams.university_project.utilities.JWTUtility;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/public")
@CrossOrigin(origins = "localhost:4200")
public class PublicController {
	
	private JWTUtility jwtUtility;
	private AuthenticationManager authenticationManager;
	
	
	@GetMapping("/")
	private String test() {
		return "hello world From Public Controller !";
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






}
