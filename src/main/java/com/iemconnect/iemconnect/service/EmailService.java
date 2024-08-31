package com.iemconnect.iemconnect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.iemconnect.iemconnect.util.Otp;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private Map<String, Otp> otpStorage = new HashMap<>();

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;

    public void sendOTP(String to, String otp) {
        String from = "rajputaniket2111@gmail.com";

        long expiryTime = Instant.now().toEpochMilli() + OTP_VALID_DURATION;
        otpStorage.put(to, new Otp(otp,expiryTime));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        emailSender.send(message);
        System.out.println("OTP email sent successfully to " + to);
    }

    public boolean validateOTP(String to, String otp) {
        Otp storedOtp = otpStorage.get(to);

        if (storedOtp == null) {
            return false; 
        }

        if (Instant.now().toEpochMilli() > storedOtp.getExpiryTime()) {
            otpStorage.remove(to); 
            return false; 
        }

       
        if (storedOtp.getOtp().equals(otp)) {
            otpStorage.remove(to); 
            return true; 
        }

        return false; 
    }
}
