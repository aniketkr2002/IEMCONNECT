package com.iemconnect.studentlog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iemconnect.studentlog.model.Comment;
import com.iemconnect.studentlog.model.Post;
import com.iemconnect.studentlog.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired 
	private PostService postService;
	
	@PostMapping(value = "/add")
	 public ResponseEntity<String> addPost(
			    @RequestParam("userName") String userName,
	            @RequestParam("dislikesCount") String dislikesCount,
	            @RequestParam("likesCount") String likesCount,
	            @RequestParam("postName") String postName,
	            @RequestParam("description") String description,
	            @RequestParam("price") String price,
	            @RequestParam("images") MultipartFile images) {

	    	
	        byte[] imageBytes = null;
	        try {
	            imageBytes = images.getBytes();
	        }catch (IOException e) {
	            return new ResponseEntity<>("Failed to convert image", HttpStatus.INTERNAL_SERVER_ERROR);
	        }


	        
	        Post post = new Post(
	        		userName,
	                postName,
	                description,
	                price,
	                imageBytes,
	                likesCount,
	                dislikesCount
	        );
	        
	        postService.addPost(post);
	        return ResponseEntity.ok("saved");
	    }
	
	@GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }
	
	@GetMapping(value = "/mypost/{userName}")
	public List<Post> getMyPost(@PathVariable("userName") String userName){
		return postService.getPost(userName);
		
	}
	
	
	@PostMapping(value = "/comment")
    public ResponseEntity<Comment> addComment( @RequestParam("postuserName") String postuserName,  @RequestParam("content") String content, @RequestParam("commentuserName") String commentuserName) {
        Comment savedComment = new Comment(content,postuserName,commentuserName);
        postService.addComment(savedComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
	@GetMapping(value ="/getuserComment/{postuserName}")
	public List<Comment> getPostComment(@PathVariable("postuserName") String postuserName){
		return postService.getComment(postuserName);
	}
	
}
