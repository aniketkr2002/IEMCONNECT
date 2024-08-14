package com.iemconnect.iemconnect.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iemconnect.iemconnect.dao.StudentDao;
import com.iemconnect.iemconnect.exception.UserAlreadyResisteredException;
import com.iemconnect.iemconnect.exception.UserNotFoundCustomException;
import com.iemconnect.iemconnect.model.College;
import com.iemconnect.iemconnect.model.Student;
import com.iemconnect.iemconnect.model.StudentEntity;
import com.iemconnect.iemconnect.service.CollegeService;
import com.iemconnect.iemconnect.service.StudentService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private CollegeService collegeService;

	@GetMapping("/getAllStudent")
	public Iterable<College> getEnrollmment(){
		return collegeService.findAllStudent();
	}
	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("/getAllRegisteredStudent")
	public Iterable<StudentEntity> getStudent(){
		return studentDao.findAll();
	}
	
	
}
