package com.iemconnect.studentlog.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iemconnect.studentlog.model.Comment;

public interface CommentDao extends CrudRepository<Comment,Integer> {

	List<Comment> findBypostuserName(String postuserName) ;

}
