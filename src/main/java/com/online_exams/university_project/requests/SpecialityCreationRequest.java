package com.online_exams.university_project.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecialityCreationRequest extends BaseCreationRequest{
	
	private long departmentId;

	public SpecialityCreationRequest(String name, String description,long departmentId) {
		super(name, description);
		this.departmentId=departmentId;
	}

}
