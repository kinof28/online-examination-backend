package com.online_exams.university_project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubscribeRequest extends BaseSubscribeRequest{
	
	private long secondDepartmentId;


}
