package com.online_exams.university_project.controllers;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_exams.university_project.dtos.AdminDTO;
import com.online_exams.university_project.dtos.ExamDTO;
import com.online_exams.university_project.dtos.StudentDTO;
import com.online_exams.university_project.dtos.TeacherDTO;
import com.online_exams.university_project.entities.Admin;
import com.online_exams.university_project.entities.Faculty;
import com.online_exams.university_project.entities.HelpMessage;
import com.online_exams.university_project.entities.Speciality;
import com.online_exams.university_project.exceptions.WrongPasswordException;
import com.online_exams.university_project.requests.AdminUpdateRequest;
import com.online_exams.university_project.requests.DegreeCreationRequest;
import com.online_exams.university_project.requests.DepartmentCreationRequest;
import com.online_exams.university_project.requests.FacultyCreationRequest;
import com.online_exams.university_project.requests.OptionCreationRequest;
import com.online_exams.university_project.requests.SpecialityCreationRequest;
import com.online_exams.university_project.requests.UpdateBranchRequest;
import com.online_exams.university_project.services.AdminServices;
import com.online_exams.university_project.services.ExamServices;
import com.online_exams.university_project.services.HelpService;
import com.online_exams.university_project.services.StudentServices;
import com.online_exams.university_project.services.TeacherServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/admin")
@CrossOrigin(origins = "localhost:4200")
public class AdminController {
	private AdminServices service;
	private StudentServices studentService;
	private TeacherServices teacherServices;
	private ExamServices examServices; 
	private HelpService helpService;
	@GetMapping("/")
	private String test() {
		return "hello world From Admin Controller !";
	}
	@GetMapping("/current")
	private AdminDTO getCurrent(@AuthenticationPrincipal Admin admin) {
		return this.service.getCurrent(admin);
	}
	@GetMapping("/help-messages")
	private List<HelpMessage> getAllHelpMessages(){
		return helpService.getAllMessages();
	}
	@GetMapping("/help-message/{id}")
	private HelpMessage getHelpMessage(@PathVariable long id){
		return helpService.getMessage(id);
	}
	@GetMapping("/students")
	private List<StudentDTO> getAllStudents(){
		return this.studentService.getAllStudents();
	}
	@GetMapping("/teachers")
	private List<TeacherDTO> getAllTeachers(){
		
		return this.teacherServices.getAllTeachers();
	}
	@GetMapping("/exams")
	private List<ExamDTO> getAllExams(){
		return this.examServices.getAllExams();
	}
	@GetMapping("/branches")
	private List<Faculty> getAllBranches(){
		return this.service.getAllFaculties();
	}
	@GetMapping("/specialities/{departmentID}")
	private List<Speciality> getAllSpecialities(@PathVariable long departmentID){
		return this.service.getAllSpecialities(departmentID);
	}
	@GetMapping("/speciality/{specialityID}")
	private Speciality getSpeciality(@PathVariable long specialityID){
		return this.service.getSpeciality(specialityID);
	}
	@PostMapping("/help-message/reply/{messageId}")
	private String replyToHelpMessage(@PathVariable long messageId,@RequestBody String context) {
		if(this.helpService.replyToHelpMessage(messageId, context)) {
			return "\"responded\"";
		}
		else return"\"error\"";
		
	}
	@PutMapping("/profil/update")
	private String updateProfilInfo(@RequestBody AdminUpdateRequest request,@AuthenticationPrincipal Admin admin) {
		try {
			if(this.service.updateData(request, admin)) {
				return "\"updated\"";
			}else return "\"Problem occured\"";
			
		}catch(WrongPasswordException exception) {
			return "wrong old password";
		}
	}

	@DeleteMapping("/help-message/delete/{id}")
	private String deleteMessage(@PathVariable long id) {
		if(this.helpService.deleteMessage(id))return "\"deleted\"";
		else return "\"bad id request\"";
	}
	@DeleteMapping("/student/delete/{id}")
	private String deleteStudent(@PathVariable long id) {
		if(this.studentService.deleteStudent(id))return "\"deleted\"";
		else return "\"bad id request\"";
	}
	@DeleteMapping("/teacher/delete/{id}")
	private String deleteTeacher(@PathVariable long id) {
		if(this.teacherServices.deleteTeacher(id))return "\"deleted\"";
		else return "\"bad id request\"";
	}
	@DeleteMapping("/exam/delete/{id}")
	private String deleteExam(@PathVariable long id) {
		if(this.examServices.deleteExam(id))return "\"deleted\"";
		else return "\"bad id request\"";
	}
	@PostMapping("/create/faculty")
	private String createFaculty(@RequestBody FacultyCreationRequest request) {
		if(this.service.createFaculty(request)) {
			return "\"created\"";
		}else return "\"echeck\"";
	}
	@PostMapping("/create/department")
	private String createDepartment(@RequestBody DepartmentCreationRequest request) {
		if(this.service.createDepartment(request)) {
			return "\"created\"";
		}else return "\"echeck\"";
	}
	@PostMapping("/create/degree")
	private String createDegree(@RequestBody DegreeCreationRequest request) {
		if(this.service.createDegree(request)) {
			return "\"created\"";
		}else return "\"echeck\"";
	}
	@PostMapping("/create/speciality")
	private String createSpeciality(@RequestBody SpecialityCreationRequest request) {
		long id=this.service.createSpeciality(request);
		if(id!=0) {
			return "\"created"+id+"\"";
		}else return "\"echeck\"";
	}
	@PostMapping("/create/option")
	private String createOption(@RequestBody OptionCreationRequest request) {
		if(this.service.createOption(request)) {
			return "\"created\"";
		}else return "\"echeck\"";
	}
	@PutMapping("/update/faculty")
	private String updateFaculty(@RequestBody UpdateBranchRequest request) {
		if(this.service.updateFaculty(request)) {
			return "\"updated\"";
		}else return "\"echeck\"";
	}
	@PutMapping("/update/department")
	private String updateDepartment(@RequestBody UpdateBranchRequest request) {
		if(this.service.updateDepartment(request)) {
			return "\"updated\"";
		}else return "\"echeck\"";
	}
	@PutMapping("/update/degree")
	private String updateDegree(@RequestBody UpdateBranchRequest request) {
		if(this.service.updateDegree(request)) {
			return "\"updated\"";
		}else return "\"echeck\"";
	}
	@PutMapping("/update/option")
	private String updateOption(@RequestBody UpdateBranchRequest request) {
		if(this.service.updateOption(request)) {
			return "\"updated\"";
		}else return "\"echeck\"";
	}
	@PutMapping("/update/speciality")
	private String updateSpeciality(@RequestBody UpdateBranchRequest request) {
		if(this.service.updateSpeciality(request)) {
			return "\"updated\"";
		}else return "\"echeck\"";
	}
	@DeleteMapping("/delete/faculty/{id}")
	private String deleteFaculty(@PathVariable long id) {
		if(this.service.deleteFaculty(id)) {
			return "\"deleted\"";
		}else return "\"echeck\"";
	}
	@DeleteMapping("/delete/department/{id}/{facultyID}")
	private String deleteDepartment(@PathVariable long id,@PathVariable long facultyID) {
		if(this.service.deleteDepartment(id,facultyID)) {
			return "\"deleted\"";
		}else return "\"echeck\"";
	}
	@DeleteMapping("/delete/degree/{id}/{departmentID}")
	private String deleteDegree(@PathVariable long id,@PathVariable long departmentID) {
		if(this.service.deleteDegree(id,departmentID)) {
			return "\"deleted\"";
		}else return "\"echeck\"";
	}
	@DeleteMapping("/delete/option/{id}/{degreeID}")
	private String deleteOption(@PathVariable long id,@PathVariable long degreeID) {
		if(this.service.deleteOption(id,degreeID)) {
			return "\"deleted\"";
		}else return "\"echeck\"";
	}
	@DeleteMapping("/delete/speciality/{id}/{departmentID}")
	private String deleteSpeciality(@PathVariable long id,@PathVariable long departmentID) {
		if(this.service.deleteSpeciality(id)) {
			return "\"deleted\"";
		}else return "\"echeck\"";
	}
	
	
}
