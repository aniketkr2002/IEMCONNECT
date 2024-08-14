package com.iemconnect.iemconnect.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.iemconnect.model.CreditScore;
@Repository
public interface CreditScoreDao extends CrudRepository<CreditScore,Long> {
	Optional<CreditScore> findByUserName(String userName);
}
