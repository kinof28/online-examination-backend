package com.online_exams.university_project.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubscribeRequest extends BaseSubscribeRequest{
	
	private long degreeId;
	private List<Long> activeOptionsIDs;


}
