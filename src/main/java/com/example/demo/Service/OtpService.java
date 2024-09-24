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
    private Impl impl;

    // Method to generate and save OTP
    public String generateOtp(String email, String phone) {
        return impl.generateOtp(email, phone); // Call the generateOtp method in Impl
    }

    // Method to verify OTP
    public boolean verifyOtp(String emailOrPhone, String otp) {
        Optional<OtpEntity> otpEntity = otpRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);
        return otpEntity.isPresent() && otpEntity.get().getOtp().equals(otp);
    }
}
