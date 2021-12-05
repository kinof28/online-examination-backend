package com.online_exams.university_project.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OptionCreationRequest extends BaseCreationRequest {
	
	
	private String level;
	@JsonProperty("SuperId")
	private long degreeID;
	private long specialityID;

	public OptionCreationRequest(String name, String description,String level,long degreeID,long specialityID) {
		super(name, description);
		this.level=level;
		this.degreeID=degreeID;
		this.specialityID=specialityID;
	}

}
