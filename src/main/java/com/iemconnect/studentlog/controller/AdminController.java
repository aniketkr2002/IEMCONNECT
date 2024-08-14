package com.iemconnect.studentlog.controller;

import com.iemconnect.studentlog.dao.StudentDao;
import com.iemconnect.studentlog.exception.UserAlreadyResisteredException;
import com.iemconnect.studentlog.exception.UserNotFoundCustomException;
import com.iemconnect.studentlog.model.College;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.model.StudentEntity;
import com.iemconnect.studentlog.service.CollegeService;
import com.iemconnect.studentlog.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
