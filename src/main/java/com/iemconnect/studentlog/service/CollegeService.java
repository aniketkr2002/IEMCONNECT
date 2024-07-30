package com.iemconnect.studentlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemconnect.studentlog.dao.CollegeDao;
import com.iemconnect.studentlog.model.College;


@Service
public class CollegeService {
	
	@Autowired
	private CollegeDao collegeDao;

	
	public College createCollegeStudent(College student) {
		return collegeDao.save(student);	
	}
	
	
	 public College findByEnrollment(String enrollment ) { 
	      return collegeDao.findByEnrollment(enrollment); }

	public Iterable<College> findAllStudent() {
		return collegeDao.findAll(); }
	 
}
