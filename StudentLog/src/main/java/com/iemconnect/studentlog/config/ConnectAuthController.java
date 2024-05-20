package com.iemconnect.studentlog.config;

import com.iemconnect.studentlog.model.ConnectJWTRequest;
import com.iemconnect.studentlog.model.ConnectJWTResponse;
import com.iemconnect.studentlog.security.ConnectJWTHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ConnectAuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private ConnectJWTHelper helper;

    private Logger logger = LoggerFactory.getLogger(ConnectAuthController.class);

    @PostMapping("/login")
    public ResponseEntity<ConnectJWTResponse> login(@RequestBody ConnectJWTRequest request){
        this.doAutheticate(request.getUserName(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.helper.generateTocken(userDetails);
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
