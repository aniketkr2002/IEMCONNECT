package com.iemconnect.studentlog.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iemconnect.studentlog.dao.CollegeDao;
import com.iemconnect.studentlog.exception.UserNotFoundCustomException;
import com.iemconnect.studentlog.model.College;
import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.service.CollegeService;

@Component
public class StudentValidate {
	
	@Autowired
	CollegeDao collegeDao;
	
	public void validate(String enrollment,String email) throws UserNotFoundCustomException{
		College college1 =collegeDao.findByEnrollment(enrollment);
		College college2 =collegeDao.findByEmail(email);
		if (college1==null) {
			throw new UserNotFoundCustomException("student not found for enrollment: " + enrollment);
		}
		if(college2==null) {
			throw new UserNotFoundCustomException("college email not found for this student: " + email + enrollment);

		}
	}
	

}
