package com.online_exams.university_project.services;


import java.util.LinkedList;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.entities.Admin;
import com.online_exams.university_project.entities.Degree;
import com.online_exams.university_project.entities.Department;
import com.online_exams.university_project.entities.Faculty;
import com.online_exams.university_project.exceptions.WrongPasswordException;
import com.online_exams.university_project.repositories.AdminRepository;
import com.online_exams.university_project.repositories.DegreeRepository;
import com.online_exams.university_project.repositories.DepartmentRepository;
import com.online_exams.university_project.repositories.FacultyRepository;
import com.online_exams.university_project.repositories.OptionRepository;
import com.online_exams.university_project.repositories.SpecialityRepository;
import com.online_exams.university_project.requests.AdminUpdateRequest;
import com.online_exams.university_project.requests.DegreeCreationRequest;
import com.online_exams.university_project.requests.DepartmentCreationRequest;
import com.online_exams.university_project.requests.FacultyCreationRequest;
import com.online_exams.university_project.requests.OptionCreationRequest;
import com.online_exams.university_project.requests.SpecialityCreationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServices {
	
	
	private AdminRepository repository;
    private PasswordEncoder passwordEncoder;
    private FacultyRepository facultyRepository;
    private DepartmentRepository departmentRepository;
    private DegreeRepository degreeRepository;
    private SpecialityRepository specialityRepository;
    private OptionRepository optionRepository;

	
	public boolean updateData(AdminUpdateRequest request,Admin admin) throws WrongPasswordException {
		Optional<Admin> adminO=this.repository.findById(admin.getId());
		if(adminO.isPresent()) {
			if(this.passwordEncoder.matches(request.getOldPassword(), adminO.get().getPassword())) {
				admin.setEmail(request.getEmail());
				admin.setFirstName(request.getFirstName());
				admin.setLastName(request.getLastName());
				admin.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
				this.repository.save(admin);
				return true;
			}else throw new WrongPasswordException();
		}else {
			return false;
		}
	}

	public boolean createFaculty(FacultyCreationRequest request) {
		Faculty faculty = new Faculty();
		faculty.setName(request.getName());
		faculty.setDescription(request.getDescription());
		faculty.setDepartments(new LinkedList<Department>());
		try{
			this.facultyRepository.save(faculty);
			return true;
		}catch(Exception e) {
			System.out.println("problem occured during creation of faculty with name "+request.getName()
								+"and here is error message"+e.getMessage());
			return false;
		}
	}
	public boolean createDepartment(DepartmentCreationRequest request) {
		try {
			Faculty faculty = this.facultyRepository.getById(request.getFacultyId());
			Department department = new Department();
			department.setName(request.getName());
			department.setDescription(request.getDescription());
			department.setDegrees(new LinkedList<Degree>());
			
			try{
				this.departmentRepository.save(department);
				faculty.getDepartments().add(department);
				try {
					this.facultyRepository.save(faculty);
					return true;
				} catch(Exception e) {
					System.out.println("problem occured durinh update of faculty to add department"+
							"and here is error message"+e.getMessage());
					return false;
				}
			}catch(Exception e) {
				System.out.println("problem occured during creation of department with name "+request.getName()
				+"and here is error message"+e.getMessage());
				return false;	
			}
			
		}catch(EntityNotFoundException e) {
			System.out.println("there is no faculty with id :"+request.getFacultyId()
			+"and here is error message"+e.getMessage());
			return false;
		}
	}
	public boolean createDegree(DegreeCreationRequest request) {
		
		return false;
	}
	public boolean createSpeciality(SpecialityCreationRequest request) {
		
		return false;
	}
	public boolean createOption(OptionCreationRequest request) {
		
		return false;
	}
	public boolean updateFaculty(OptionCreationRequest request) {
		
		return false;
	}
	public boolean updateDepartment(OptionCreationRequest request) {
		
		return false;
	}
	public boolean updateDegree(OptionCreationRequest request) {
		
		return false;
	}
	public boolean updateSpeciality(OptionCreationRequest request) {
		
		return false;
	}
	public boolean updateOption(OptionCreationRequest request) {
		
		return false;
	}
	
	public boolean deleteFaculty(OptionCreationRequest request) {
		
		return false;
	}
	public boolean deleteDepartment(OptionCreationRequest request) {
		
		return false;
	}
	public boolean deleteDegree(OptionCreationRequest request) {
		
		return false;
	}
	public boolean deleteSpeciality(OptionCreationRequest request) {
		
		return false;
	}
	public boolean deleteOption(OptionCreationRequest request) {
		
		return false;
	}

}
