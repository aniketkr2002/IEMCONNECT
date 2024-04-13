package com.iemconnect.studentlog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class College {
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	@Column(nullable = false)
	@Size(min=2,max=20,message = "Name should atleast two charachter")
	private String name;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	@Column(nullable = false)
    private String email;
	
	
	@NotBlank(message = "Phone is required")
	@Column(nullable = false)
	private String phone;
	
	@NotBlank(message = "Enrollment is required")
	@Column(nullable = false)
	private String enrollment;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public String toString() {
		return "College [studentId=" + studentId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", enrollment=" + enrollment + "]";
	} 
		
	
}
