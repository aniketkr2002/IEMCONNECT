package com.iemconnect.studentlog.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iemconnect.studentlog.dao.StudentDao;
import com.iemconnect.studentlog.model.StudentEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StudentEntity> userOptional = studentDao.findByUserName(username);

        if (userOptional.isPresent()) {
            StudentEntity user = userOptional.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(passwordEncoder().encode(user.getPassword())) 
                    .roles("USER") 
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
    {
        return builder.getAuthenticationManager();
    }
}
