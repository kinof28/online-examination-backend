package com.online_exams.university_project.mappers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.online_exams.university_project.dtos.AdminDTO;
import com.online_exams.university_project.entities.Admin;

@Component
public class AdminMapper implements Mapper<Admin, AdminDTO> {

	@Override
	public AdminDTO getOneDTO(Admin admin) {
		return new AdminDTO(admin.getEmail(),admin.getFirstName(),admin.getLastName());
	}
	// this is an extra method never used since we have on admin
	@Override
	public List<AdminDTO> getAllDTOs(List<Admin> admins) {
		List<AdminDTO> adminsDTOs =new LinkedList<AdminDTO>();
		for(Admin admin:admins) {
			adminsDTOs.add(this.getOneDTO(admin));
		}
		return adminsDTOs;
	}

}
