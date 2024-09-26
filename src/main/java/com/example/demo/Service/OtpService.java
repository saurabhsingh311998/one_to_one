package com.example.demo.Service;
import com.example.demo.Impl.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class OtpService {
    @Autowired
    private Impl impl;

    // Method to generate and save OTP
    public String generateOtp(String email, String phone) {
        return impl.generateOtp(email, phone); // Call the generateOtp method in Impl
    }

    // Method to verify OTP
    public boolean verifyOtp(String emailOrPhone, String otp) {
        Impl.OtpInfo otpInfo = impl.otpStore.get(emailOrPhone);
        if (otpInfo != null) {
            if (otpInfo.getOtp().equals(otp) && LocalDateTime.now().isBefore(otpInfo.getExpirationTime())) {
                impl.otpStore.remove(emailOrPhone); // OTP is used, remove it
                return true;
            }
        }
        return false;
    }
}
