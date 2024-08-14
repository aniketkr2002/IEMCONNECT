package com.iemconnect.iemconnect.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iemconnect.iemconnect.exception.UserAlreadyResisteredException;
import com.iemconnect.iemconnect.exception.UserNotFoundCustomException;
import com.iemconnect.iemconnect.model.Student;
import com.iemconnect.iemconnect.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {
	@Autowired
	private StudentService studentService;
	
	public UserController(StudentService studentService) {
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
//	@PostMapping(value = "/login")
//	public ResponseEntity<Object> loginUser(@RequestBody LoginPage user) {
//	    StudentEntity authenticatedUser = studentService.authenticate(user.getUserName(), user.getPassword());
//	    if (authenticatedUser != null) {
//	        return ResponseEntity.ok(authenticatedUser);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
//	    }
//	
//     }
	
	 

}
