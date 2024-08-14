package com.iemconnect.iemconnect.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iemconnect.iemconnect.dao.CollegeDao;
import com.iemconnect.iemconnect.exception.UserNotFoundCustomException;
import com.iemconnect.iemconnect.model.College;
import com.iemconnect.iemconnect.model.Student;
import com.iemconnect.iemconnect.service.CollegeService;

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
			throw new UserNotFoundCustomException("college email not found for this student: " + email);

		}
	}
	

}
