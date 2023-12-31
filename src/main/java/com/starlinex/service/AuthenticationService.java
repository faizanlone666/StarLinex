package com.starlinex.service;

import com.starlinex.exception.StarLinexException;
import com.starlinex.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

    String resetPassword(OtpId otpId) throws StarLinexException;

    EmailMsg sendOtpForForgetPassword(String email) throws StarLinexException;
}
