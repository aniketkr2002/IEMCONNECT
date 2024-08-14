package com.iemconnect.iemconnect.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iemconnect.iemconnect.model.Comment;

public interface CommentDao extends CrudRepository<Comment,Integer> {

	List<Comment> findBypostuserName(String postuserName) ;

}
