package com.iemconnect.iemconnect.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.iemconnect.model.StudentCreditScore;
@Repository
public interface StudentCreditScoreServiceDao extends CrudRepository<StudentCreditScore,Long> {

}
