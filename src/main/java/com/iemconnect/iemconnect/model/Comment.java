package com.iemconnect.iemconnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comment {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String content;
	    @NotBlank(message = "username is required")
	    
	    @NotNull
	    private String postuserName;
	    
	    @ManyToOne
	    private Post post;
	    
	    @NotBlank(message = "username is required")
	    @NotNull
	    private String commentuserName;

	    public String getPostuserName() {
			return this.postuserName;
		}

		public void setPostuserName(String postuserName) {
			this.postuserName = postuserName;
		}

		public String getCommentuserName() {
			return this.commentuserName;
		}

		public void setCommentuserName(String commentuserName) {
			this.commentuserName = commentuserName;
		}

		// Constructors, getters, and setters
	    public Comment() {}

	   
		public Comment(String content,
				 String postuserName,String commentuserName) {
			this.content = content;
			this.postuserName = postuserName;
			this.commentuserName = commentuserName;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

}
