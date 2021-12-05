package com.online_exams.university_project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateBranchRequest {
	private long id;
	private String name;
	private String description;

}
