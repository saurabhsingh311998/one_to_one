package com.example.demo.Controller;

import com.example.demo.Entity.ResponseEntity;
import com.example.demo.Impl.Impl;
import com.example.demo.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {
    @Autowired
    private OtpService otpService;

    // Endpoint to generate OTP
    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestBody Impl.GenerateOtpRequest request) {
        String otp = otpService.generateOtp(request.getEmail(), request.getPhone());
        return new ResponseEntity<>(false, 200, "OTP generated successfully: " + otp);
    }

    // Endpoint to verify OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Impl.VerifyOtpRequest request) {
        boolean isValid = otpService.verifyOtp(request.getEmailOrPhone(), request.getOtp());
        if (isValid) {
            return new ResponseEntity<>(false, 200, "OTP verified successfully!");
        } else {
            return new ResponseEntity<>(true, 400, "Invalid OTP!");
        }
    }
}
