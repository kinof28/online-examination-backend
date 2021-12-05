package com.online_exams.university_project.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DegreeCreationRequest extends BaseCreationRequest {
	
	private String degreeType;
	@JsonProperty("SuperId")
	private long departmentId;

	public DegreeCreationRequest(String name, String description,String degreeType,long departmentId) {
		super(name, description);
		this.degreeType=degreeType;
		this.departmentId=departmentId;
	}

}
