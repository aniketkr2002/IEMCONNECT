package com.iemconnect.studentlog.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.iemconnect.studentlog.model.Student;
import com.iemconnect.studentlog.model.StudentEntity;

@Repository
public interface StudentDao extends CrudRepository<StudentEntity,Integer> {

	Optional<StudentEntity> findByEmailIgnoreCase(String email);

	Optional<StudentEntity> findByEnrollmentIgnoreCase(String enrollment);

	Optional<StudentEntity>  findByUserName(String userName);
	
}
