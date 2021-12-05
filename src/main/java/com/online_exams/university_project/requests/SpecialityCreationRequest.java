package com.online_exams.university_project.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecialityCreationRequest extends BaseCreationRequest{
	
	@JsonProperty("SuperId")
	private long departmentId;

	public SpecialityCreationRequest(String name, String description,long departmentId) {
		super(name, description);
		this.departmentId=departmentId;
	}

}
