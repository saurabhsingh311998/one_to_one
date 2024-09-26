package com.example.demo.Impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
@Service
public class Impl {

    public   Map<String, OtpInfo> otpStore = new HashMap<>(); // In-memory OTP store
    private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 4;

    @Autowired
    JavaMailSender javaMailSender;

    // Method to generate OTP and send it via email
    public String generateOtp(String email, String phone) {
        String otp = generateOTP(); // Call a method to generate the OTP

        // Store OTP and its expiration time in the HashMap
        OtpInfo otpInfo = new OtpInfo(email, phone, otp, LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes
//        otpStore.put(email != null ? email : phone, otpInfo);
        Stream.of(email, phone).filter(Objects::nonNull).forEach(key -> otpStore.put(key, otpInfo));
        if (email != null) {
            sendOtpEmail(email, otp);
        }

        // Simulate sending OTP via SMS
        // if (phone != null) sendOtpSms(phone, otp);

        return otp;
    }

    private String generateOTP() {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));  // Generate a random digit
        }
        return otp.toString();
    }

    // Helper class to store OTP and expiration time
    @Data
    public static class OtpInfo {
        private String otp;
        private String email;
        private String phone;
        private LocalDateTime expirationTime;

        public OtpInfo(String email, String phone, String otp, LocalDateTime expirationTime) {
            this.email = email;
            this.phone = phone;
            this.otp = otp;
            this.expirationTime = expirationTime;
        }
    }

//    private void sendOtpSms(String phone, String otp) {
//        twilioService.sendSms(phone, "Your OTP code is: " + otp);
//    }

    // Method to send the OTP via email
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Dear User,\n\nPlease use the following 4-digit OTP for my account verification:" + otp + ". Kindly ensure that this information remains confidential, as the OTP will expire in 5 minutes.\n\nThank you!\n\nBest,\nSaurabh Singh");
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
