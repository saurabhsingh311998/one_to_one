package com.example.demo.Controller;

import com.example.demo.Entity.ResponseEntity;
import com.example.demo.Impl.Impl;
import com.example.demo.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {
    @Autowired
    private OtpService otpService;

    // Endpoint to generate OTP

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestBody Impl.GenerateOtpRequest request) {
        try {
            String otp = otpService.generateOtp(request.getEmail(), request.getPhone());
            return new ResponseEntity<>(false, HttpStatus.OK.value(),null, "OTP generated successfully: ");
        } catch (Exception ex) {
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR.value(),null, "Error generating OTP");
        }
    }

    // Endpoint to verify OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Impl.VerifyOtpRequest request) {
        boolean isValid = otpService.verifyOtp(request.getEmailOrPhone(), request.getOtp());
        try {
            if (isValid) {
                return new ResponseEntity<>(false, HttpStatus.OK.value(),null, "OTP verified successfully!");
            } else {
                return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST.value(),null, "Invalid or expired OTP!");
            }
        }catch (Exception ex){
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), null,"Internal server error!" );
        }
    }
}
