package com.iemconnect.studentlog.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.studentlog.model.College;

@Repository
public interface CollegeDao extends CrudRepository<College, Integer>{

	public College findByEnrollment(String enrollment);

	public College findByEmail(String email);
	
	

}
