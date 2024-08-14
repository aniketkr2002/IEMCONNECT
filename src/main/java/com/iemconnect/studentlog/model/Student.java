package com.iemconnect.studentlog.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class Student {

	
	@Column(nullable = false)
	@Size(min=2,max=20,message = "Name should atleast two charachter and unique")
	private String userName;
	
	@Column(nullable = false)
	@Size(min=2,max=20,message = "Name should atleast two charachter")
	private String name;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format/College email required")
	@Column(nullable = false)
    private String email;
	
	@NotBlank(message="current year is required")
	@Column(nullable = false)
	private String curryear;
	
	@NotBlank(message = "current semester is required")
	@Column(nullable = false)
	private String semester;
	
	@NotBlank(message = "Phone is required")
	@Column(nullable = false)
	private String phone;
	
	@NotBlank(message = "Enrollment is required")
	@Column(nullable = false)
	private String enrollment;
	
	@NotBlank(message = "Branch is required")
	@Column(nullable=false)
	private String branch;
	
	@NotBlank(message = "Password is required")
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",message = " password to contain at least one letter, one digit, and be at least 6 characters long")
//	@Column(nullable=false)
//	@Size(min=5,message = "password to contain at least one letter, one digit, and be at least 6 characters long")
	private String password;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurryear() {
		return curryear;
	}

	public void setCurryear(String curryear) {
		this.curryear = curryear;
	}
	
	
	
	public Student() {}
	
	public Student(String name, String email, String curryear, String semester, String phone,
			String enrollment, String branch, String password) {
		super();
		this.name = name;
		this.email = email;
		this.curryear = curryear;
		this.semester = semester;
		this.phone = phone;
		this.enrollment = enrollment;
		this.branch = branch;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
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

	public String getName() {
		return name;
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Student [ name=" + name + ", email=" + email + ", year=" + curryear
				+ ", semester=" + semester + ", phone=" + phone + ", enrollment=" + enrollment + ", branch=" + branch
				+ ", password=" + password + "]";
	}
	
	
	
}
