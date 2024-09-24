package com.example.demo.Controller;

import com.example.demo.Entity.ResponseEntity;
import com.example.demo.Impl.Impl;
import com.example.demo.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try{
            String otp = otpService.generateOtp(request.getEmail(), request.getPhone());
            return new ResponseEntity<>(false, HttpStatus.OK.value(), "OTP generated successfully: " + otp);
        }catch (Exception ex){
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

    // Endpoint to verify OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Impl.VerifyOtpRequest request) {
        try {
            boolean isValid = otpService.verifyOtp(request.getEmailOrPhone(), request.getOtp());
            if (isValid) {
                return new ResponseEntity<>(false, HttpStatus.OK.value(), "OTP verified successfully!");
            } else {
                return new ResponseEntity<>(true, 400, "Invalid OTP!");
            }
        }catch (Exception ex){
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }
}
