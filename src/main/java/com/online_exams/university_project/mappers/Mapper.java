package com.online_exams.university_project.mappers;

import java.util.List;

public interface Mapper<From, To> {
	
	To getOneDTO(From entity);
	List<To> getAllDTOs (List<From> entities);

}
