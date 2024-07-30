package com.iemconnect.studentlog.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.iemconnect.studentlog.model.Post;

@Repository
public interface PostDao extends ListCrudRepository<Post,Integer> {

	List<Post> findByuserName(String userName);

	Optional<Post> findById(Long postId);


}
