package com.iemconnect.studentlog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.studentlog.model.StudentCreditScore;
@Repository
public interface StudentCreditScoreServiceDao extends CrudRepository<StudentCreditScore,Long> {

}
