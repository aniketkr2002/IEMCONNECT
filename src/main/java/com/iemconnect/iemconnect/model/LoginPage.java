package com.iemconnect.iemconnect.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginPage {
	@NotNull
	@NotBlank
	private String userName;
	
	@NotNull
	@NotBlank
	private String password;
	
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	
	
	
}
