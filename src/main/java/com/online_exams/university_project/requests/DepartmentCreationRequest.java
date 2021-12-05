package com.online_exams.university_project.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentCreationRequest extends BaseCreationRequest {
	
	@JsonProperty("SuperId")
	private long facultyId;
	
	public DepartmentCreationRequest(String name, String description,long facultyId) {
		super(name, description);
		this.facultyId=facultyId;
	}

}
