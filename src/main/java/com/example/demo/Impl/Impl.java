package com.example.demo.Impl;

import com.example.demo.Entity.OtpEntity;
import com.example.demo.Repository.OtpRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
@Service
public class Impl {
    private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 4;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OtpRepository otpRepository;

//    @Autowired
//    private TwilioService twilioService; // For sending SMS


    // Method to generate OTP and send it via email
    public String generateOtp(String email, String phone) {
        String otp = generateOTP();

        // Save OTP to database (assuming OtpEntity has email, phone, and otp fields)
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setPhone(phone); // Assuming you still want to store phone
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);

        // Send OTP via email
        sendOtpEmail(email, otp);

        // If you're still planning to send OTP via SMS, uncomment this
        // sendOtpSms(phone, otp);

        return otp;
    }

    private String generateOTP() {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));  // Generate a random digit
        }
        return otp.toString();
    }

//    private void sendOtpSms(String phone, String otp) {
//        twilioService.sendSms(phone, "Your OTP code is: " + otp);
//    }

    // Method to send the OTP via email
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Dear User,\n\nPlease use the following 4-digit OTP for my account verification: " + otp + ". Kindly ensure that this information remains confidential.\n\nThank you!\n\nBest,\nSaurabh Singh");
        message.setFrom("saurabhsingh03011998@gmail.com"); // Update this to your verified email

        javaMailSender.send(message);
    }

    // You can reuse this method if you need to send other emails
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("saurabhsingh03011998@gmail.com");

        javaMailSender.send(message);
    }

    @Data
    public static class GenerateOtpRequest {
        private String email;
        private String phone;
    }

    @Data
    public static class VerifyOtpRequest {
        private String emailOrPhone;
        private String otp;
    }
}
