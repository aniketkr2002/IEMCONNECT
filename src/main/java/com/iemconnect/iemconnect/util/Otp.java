package com.iemconnect.iemconnect.util;

import org.springframework.stereotype.Component;

@Component
public class Otp {
    private String otp;
    private long expiryTime;

    public Otp(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

   
   public Otp() {
	   
   }



	public String getOtp() {
        return otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
