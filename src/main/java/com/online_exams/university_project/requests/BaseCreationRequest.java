package com.online_exams.university_project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public abstract class BaseCreationRequest {
	
	private String name;
	private String description;

}
