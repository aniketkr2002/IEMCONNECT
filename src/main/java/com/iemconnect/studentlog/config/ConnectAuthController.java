package com.iemconnect.studentlog.config;


import com.iemconnect.studentlog.model.ConnectJWTResponse;
import com.iemconnect.studentlog.model.LoginPage;
import com.iemconnect.studentlog.security.ConnectJWTHelper;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ConnectAuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private ConnectJWTHelper helper;
//
    private Logger logger = LoggerFactory.getLogger(ConnectAuthController.class);

    @PostMapping("/login")
    public ResponseEntity<ConnectJWTResponse> login(@Valid @RequestBody LoginPage user /*ConnectJWTRequest request*/){
        this.doAutheticate(user.getUserName(),user.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        String token =  this.helper.generateToken(userDetails);
        ConnectJWTResponse response = ConnectJWTResponse.builder().jwtToken(token).userName(userDetails
                .getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAutheticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,password);
        try{
            manager.authenticate(authentication);
        }
        catch(BadCredentialsException e){
            throw new RuntimeException("Invalid username and password");
        }
    }
}
