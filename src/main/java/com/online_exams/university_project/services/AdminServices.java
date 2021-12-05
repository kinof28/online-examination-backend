package com.online_exams.university_project.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.dtos.AdminDTO;
import com.online_exams.university_project.entities.Admin;
import com.online_exams.university_project.entities.Degree;
import com.online_exams.university_project.entities.Department;
import com.online_exams.university_project.entities.Faculty;
import com.online_exams.university_project.entities.Option;
import com.online_exams.university_project.entities.Speciality;
import com.online_exams.university_project.enums.DegreeType;
import com.online_exams.university_project.enums.Level;
import com.online_exams.university_project.exceptions.WrongPasswordException;
import com.online_exams.university_project.mappers.AdminMapper;
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
import com.online_exams.university_project.requests.UpdateBranchRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServices {

	private AdminRepository repository;
	private AdminMapper mapper;
	private PasswordEncoder passwordEncoder;
	private FacultyRepository facultyRepository;
	private DepartmentRepository departmentRepository;
	private DegreeRepository degreeRepository;
	private SpecialityRepository specialityRepository;
	private OptionRepository optionRepository;

	public void initializeAdmin(String email, String password) {
		if (this.repository.count() < 1) {
			Admin admin = new Admin();
			admin.setEmail("admin_" + email);
			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setPassword(this.passwordEncoder.encode(password));
			admin.setActivated(true);
			this.repository.save(admin);
		}
	}

	public AdminDTO getCurrent(Admin admin) {
		admin.setEmail(admin.getEmail().substring(6));
		return this.mapper.getOneDTO(admin);
	}

	public boolean updateData(AdminUpdateRequest request, Admin admin) throws WrongPasswordException {
		Optional<Admin> adminO = this.repository.findById(admin.getId());
		if (adminO.isPresent()) {
			if (request.getOldPassword()==null||request.getOldPassword().isBlank() || request.getOldPassword().isEmpty())
				return false;
			if (this.passwordEncoder.matches(request.getOldPassword(), adminO.get().getPassword())) {
				if (!(request.getFirstName()==null || request.getFirstName().isBlank() || request.getFirstName().isEmpty()))
					admin.setFirstName(request.getFirstName());
				if (!(request.getLastName()==null || request.getLastName().isBlank() || request.getLastName().isEmpty()))
					admin.setLastName(request.getLastName());
				if (!(request.getNewPassword()==null || request.getNewPassword().isBlank() || request.getNewPassword().isEmpty()))
					admin.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
				this.repository.save(admin);
				return true;
			} else
				throw new WrongPasswordException();
		} else {
			return false;
		}
	}
	
	public List<Faculty> getAllFaculties(){
		return this.facultyRepository.findAll();
	}
	public List<Speciality> getAllSpecialities(long departmentID){
		Optional<Department> departmentO=this.departmentRepository.findById(departmentID);
		if(departmentO.isPresent())	return this.specialityRepository.findByDepartement(departmentO.get());
		else return null;
	}
	public Speciality getSpeciality(long specialityId) {
		Optional<Speciality> specialityO=this.specialityRepository.findById(specialityId);
		return specialityO.isPresent()?specialityO.get():null;
	}

	public boolean createFaculty(FacultyCreationRequest request) {
		Faculty faculty = new Faculty();
		faculty.setName(request.getName());
		faculty.setDescription(request.getDescription());
		faculty.setDepartments(new LinkedList<Department>());
		try {
			this.facultyRepository.save(faculty);
			return true;
		} catch (Exception e) {
			System.out.println("problem occured during creation of faculty with name " + request.getName()
					+ "and here is error message" + e.getMessage());
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

			try {
				this.departmentRepository.save(department);
				faculty.getDepartments().add(department);
				try {
					this.facultyRepository.save(faculty);
					return true;
				} catch (Exception e) {
					System.out.println("problem occured durinh update of faculty to add department"
							+ " and here is error message" + e.getMessage());
					return false;
				}
			} catch (Exception e) {
				System.out.println("problem occured during creation of department with name " + request.getName()
						+ " and here is error message " + e.getMessage());
				return false;
			}

		} catch (EntityNotFoundException e) {
			System.out.println("there is no faculty with id :" + request.getFacultyId() + "and here is error message"
					+ e.getMessage());
			return false;
		}
	}

	public boolean createDegree(DegreeCreationRequest request) {
		try {
			Department department = this.departmentRepository.getById(request.getDepartmentId());
			Degree degree = new Degree();
			degree.setName(request.getName());
			degree.setDescription(request.getDescription());
			degree.setType(DegreeType.valueOf(request.getDegreeType()));
			degree.setOptions(new LinkedList<>());
			this.degreeRepository.save(degree);
			department.getDegrees().add(degree);
			this.departmentRepository.save(department);
			return true;
		} catch (Exception e) {
			System.out.println("something went wrong in creation of degree in departement with id :"
					+ request.getDepartmentId() + "and here is error message" + e.getMessage());
			return false;
		}
	}

	public long createSpeciality(SpecialityCreationRequest request) {
		Speciality speciality = new Speciality();
		speciality.setName(request.getName());
		speciality.setDescription(request.getDescription());
		try {
			speciality.setDepartement(this.departmentRepository.getById(request.getDepartmentId()));
			return this.specialityRepository.save(speciality).getId();
			
		} catch (Exception e) {
			System.out.println("something went wrong in creation of speciality with name :" + request.getName()
					+ "and here is error message" + e.getMessage());
			return 0l;
		}
	}

	public boolean createOption(OptionCreationRequest request) {
		try {
			Degree degree = this.degreeRepository.getById(request.getDegreeID());
			Option option = new Option();
			option.setName(request.getName());
			option.setDescription(request.getDescription());
			option.setSpeciality(this.specialityRepository.getById(request.getSpecialityID()));
			option.setLevel(Level.valueOf(request.getLevel()));
			this.optionRepository.save(option);
			degree.getOptions().add(option);
			this.degreeRepository.save(degree);
			return true;

		} catch (Exception e) {
			System.out.println("something went wrong in creation of Option in degree with id :" + request.getDegreeID()
					+ "and speciality with id :" + request.getSpecialityID() + "and here is error message"
					+ e.getMessage());
			return false;
		}
	}

	public boolean updateFaculty(UpdateBranchRequest request) {
		Optional<Faculty> facultyO=this.facultyRepository.findById(request.getId());
		if(facultyO.isPresent()) {
			Faculty faculty = facultyO.get();
			if(request.getName()!=null&&!request.getName().isBlank())faculty.setName(request.getName());
			if(request.getDescription()!=null&&!request.getDescription().isBlank())faculty.setDescription(request.getDescription());
			this.facultyRepository.save(faculty);
			return true;
		}else return false;
	}

	public boolean updateDepartment(UpdateBranchRequest request) {
		
		Optional<Department> departmentO=this.departmentRepository.findById(request.getId());
		if(departmentO.isPresent()) {
			Department department = departmentO.get();
			if(request.getName()!=null&&!request.getName().isBlank())department.setName(request.getName());
			if(request.getDescription()!=null&&!request.getDescription().isBlank())department.setDescription(request.getDescription());
			this.departmentRepository.save(department);
			return true;
		}else return false;
	}

	public boolean updateDegree(UpdateBranchRequest request) {
		Optional<Degree> degreeO=this.degreeRepository.findById(request.getId());
		if(degreeO.isPresent()) {
			Degree degree = degreeO.get();
			if(request.getName()!=null&&!request.getName().isBlank())degree.setName(request.getName());
			if(request.getDescription()!=null&&!request.getDescription().isBlank())degree.setDescription(request.getDescription());
			this.degreeRepository.save(degree);
			return true;
		}else return false;
	}

	public boolean updateSpeciality(UpdateBranchRequest request) {
		Optional<Speciality> specialityO=this.specialityRepository.findById(request.getId());
		if(specialityO.isPresent()) {
			Speciality speciality= specialityO.get();
			if(request.getName()!=null&&!request.getName().isBlank())speciality.setName(request.getName());
			if(request.getDescription()!=null&&!request.getDescription().isBlank())speciality.setDescription(request.getDescription());
			this.specialityRepository.save(speciality);
			return true;
		}else return false;
	}

	public boolean updateOption(UpdateBranchRequest request) {
		Optional<Option> optionO=this.optionRepository.findById(request.getId());
		if(optionO.isPresent()) {
			Option option = optionO.get();
			if(request.getName()!=null&&!request.getName().isBlank())option.setName(request.getName());
			if(request.getDescription()!=null&&!request.getDescription().isBlank())option.setDescription(request.getDescription());
			this.optionRepository.save(option);
			return true;
		}else return false;
	}

	public boolean deleteFaculty(Long id) {
		try {
			this.facultyRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;			
		}
	}

	public boolean deleteDepartment(Long id,Long facultyID) {
		try {
			Optional<Faculty> facultyO=this.facultyRepository.findById(facultyID);
			Optional<Department> department0=this.departmentRepository.findById(id);
			if(facultyO.isPresent()&&department0.isPresent()) {
				Faculty faculty =facultyO.get();
				faculty.getDepartments().remove(department0.get());
				this.facultyRepository.save(faculty);
				this.departmentRepository.delete(department0.get());
				return true;
				
			}else return false;
		}catch(Exception e) {
			return false;			
		}
	}

	public boolean deleteDegree(Long id,Long departmentID) {
		try {
			Optional<Department> department0=this.departmentRepository.findById(departmentID);
			Optional<Degree> degreeO=this.degreeRepository.findById(id);
			if(department0.isPresent()&&degreeO.isPresent()) {
				Department department=department0.get();
				department.getDegrees().remove(degreeO.get());
				this.departmentRepository.save(department);
				this.degreeRepository.delete(degreeO.get());;
				return true;
				
			}else return false;
		}catch(Exception e) {
			return false;			
		}
	}

	public boolean deleteSpeciality(Long id) {
		try {
			this.specialityRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;			
		}
	}

	public boolean deleteOption(Long id,Long degreeID) {
		try {
			Optional<Degree> degreeO=this.degreeRepository.findById(degreeID);
			Optional<Option> optionO=this.optionRepository.findById(id);
			if(degreeO.isPresent()&&optionO.isPresent()) {
				Degree degree = degreeO.get();
				degree.getOptions().remove(optionO.get());
				this.degreeRepository.save(degree);
				this.optionRepository.delete(optionO.get());;
				return true;
			}else return false;
		}catch(Exception e) {
			return false;			
		}
	}

}
