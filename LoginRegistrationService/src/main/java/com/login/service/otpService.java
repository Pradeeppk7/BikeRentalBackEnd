package com.login.service;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class otpService {
private  Logger logger =  LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	private SecureRandom secureRandom = new SecureRandom();
	private static final int OTP_LENGTH = 6;
    private static final String NUMERIC_CHARACTERS = "0123456789";
    
	public String generateOtp() {
        StringBuilder otpBuilder = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = secureRandom.nextInt(NUMERIC_CHARACTERS.length());
            otpBuilder.append(NUMERIC_CHARACTERS.charAt(index));
        }

        return otpBuilder.toString();
    }

   

    public void sendOtp(String recipientEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        try {
            logger.info("Sending OTP to {}", recipientEmail);
            mailSender.send(message);
            logger.info("OTP sent successfully to {}", recipientEmail);
        } catch (Exception e) {
            logger.error("Failed to send OTP to {}: {}", recipientEmail, e.getMessage(), e);
        }
    }
}
