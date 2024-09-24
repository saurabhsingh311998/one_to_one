package com.example.demo.Service;

import com.example.demo.Entity.OtpEntity;
import com.example.demo.Impl.Impl;
import com.example.demo.Repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    Impl impl;

    // Method to generate and save OTP
    public String generateOtp(String email, String phone) {
        Random random = new Random();
        String otp = String.format("%04d", random.nextInt(10000)); // Generate 4 digit OTP

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setPhone(phone);
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);

        // Send OTP via email
        impl.sendOtpEmail(email, otp);
        return otp;
    }

    // Method to verify OTP
    public boolean verifyOtp(String emailOrPhone, String otp) {
        Optional<OtpEntity> otpEntity = otpRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);
        return otpEntity.isPresent() && otpEntity.get().getOtp().equals(otp);
    }
}
