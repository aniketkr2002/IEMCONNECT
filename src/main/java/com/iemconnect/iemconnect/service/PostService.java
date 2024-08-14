package com.iemconnect.iemconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemconnect.iemconnect.dao.CommentDao;
import com.iemconnect.iemconnect.dao.PostDao;
import com.iemconnect.iemconnect.model.Comment;
import com.iemconnect.iemconnect.model.Post;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public List<Post> getAllPost() {
		return   postDao.findAll();
	}
	
	public Post addPost(Post post) {
		 return  postDao.save(post);
	}

	

	public List<Post> getPost(String userName) {
		return postDao.findByuserName(userName);
	}
	
	@Autowired
    private CommentDao commentDao;

    
    public Comment addComment(Comment comment) {
        return commentDao.save(comment);
    }

	public  List<Comment> getComment(String postuserName) {
		return commentDao.findBypostuserName(postuserName);
	}
   
	
}
