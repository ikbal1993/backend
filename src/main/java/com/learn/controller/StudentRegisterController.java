package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.model.Student;
import com.learn.repository.StudentRepository;


@RestController
@CrossOrigin("*")
public class StudentRegisterController {

	@Autowired
	private StudentRepository studentRepo;
	
	//---> http://localhost:9090/register/save
	
//	@PostMapping("/register/save")
	@RequestMapping(value="/register/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUserData(@RequestBody Student student) {
		
		System.out.println("***********tets*******");
	
		if (student.getGender().isEmpty()) {
			
			return new ResponseEntity<>("Please Select Your gender",HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(studentRepo.existsByEmail(student.getEmail())) {
			return new ResponseEntity<>("Student already existed",HttpStatus.ALREADY_REPORTED);
		}
		
		student.setRole("USER");
		
		this.studentRepo.save(student);
		
		return new ResponseEntity<>("Successfully Registration Done",HttpStatus.OK);
	}
	
	@GetMapping("/register/getAll")
	public ResponseEntity<?> getAllStudent() {
		List<Student> findAll = this.studentRepo.findAll();
		
		return new ResponseEntity<>(findAll,HttpStatus.OK);
	}
	
}

   


