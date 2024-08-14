package com.iemconnect.iemconnect.service;

import java.util.Date;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemconnect.iemconnect.dao.StudentDao;
import com.iemconnect.iemconnect.exception.UserAlreadyResisteredException;
import com.iemconnect.iemconnect.exception.UserNotFoundCustomException;
import com.iemconnect.iemconnect.model.Student;
import com.iemconnect.iemconnect.model.StudentEntity;
import com.iemconnect.iemconnect.validation.RegistrationValidate;
import com.iemconnect.iemconnect.validation.StudentValidate;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	@Autowired
	StudentValidate studentValidate;
	@Autowired 
	RegistrationValidate registrationValidate;

	public StudentEntity createStudent(Student studentBean) throws UserNotFoundCustomException {

		try {
			studentValidate.validate(studentBean.getEnrollment(),studentBean.getEmail());
			registrationValidate.validate(studentBean);
			StudentEntity student = new StudentEntity();
            setAuditFields(student,studentBean);
            mapStudentBeanToStudentEntity(student,studentBean);

				return studentDao.save(student);
		} catch (Exception e){
				//System.out.println("exception ::::: " + e.getMessage());
				throw e;
		}
	}

	private void setAuditFields(StudentEntity student,Student studentbean) {
		Date now =new Date();
		student.setCreatedAt(now);
		student.setCreatedBy(studentbean.getName());
		student.setLastUpdatedBy(studentbean.getName());
		student.setLastUpdatedAt(now);
		
	}

	private void mapStudentBeanToStudentEntity(StudentEntity student, Student studentBean) {
		student.setName(studentBean.getName());
        student.setEnrollment(studentBean.getEnrollment());
        student.setEmail(studentBean.getEmail());
        student.setCurryear(studentBean.getCurryear());
        student.setBranch(studentBean.getPassword());
        student.setPhone(studentBean.getPhone());
        student.setSemester(studentBean.getSemester());
        student.setUserName(studentBean.getUserName());
		student.setPassword(studentBean.getPassword());
	}
	public StudentEntity authenticate(String username, String password) {
		Optional<StudentEntity> optionalUser = studentDao.findByUserName(username);
		System.out.println(optionalUser);

        if (optionalUser.isPresent()) {
            StudentEntity user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user; // Authentication successful
            }
        }
        return null;
    }
	
	

	public Optional<StudentEntity> getStudent(String userName) {
		Optional<StudentEntity>  st =  studentDao.findByUserName(userName);
		if(st.isPresent()) {
			return st;
		}
		return null;

	}
	
	
	
}
