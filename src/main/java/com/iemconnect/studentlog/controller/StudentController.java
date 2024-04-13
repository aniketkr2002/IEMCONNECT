package com.iemconnect.studentlog.controller;

import com.iemconnect.studentlog.model.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iemconnect.studentlog.exception.UserAlreadyResisteredException;
import com.iemconnect.studentlog.exception.UserNotFoundCustomException;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	
	
	@PostMapping(value = "/register")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
	
		try {
			studentService.createStudent(student);
			return ResponseEntity.ok("saved");
	     } 
		catch (UserNotFoundCustomException e) 
		{
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(e.getMessage());
		}catch (UserAlreadyResisteredException e)
		{
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body( e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginPage user){
		boolean isAuthenticated = studentService.authenticate(user.getUserName(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        }
	}

	
}
