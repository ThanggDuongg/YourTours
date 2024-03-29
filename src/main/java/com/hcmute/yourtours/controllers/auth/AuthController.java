package com.hcmute.yourtours.controllers.auth;


import com.hcmute.yourtours.factories.auth.IAuthFactory;
import com.hcmute.yourtours.libs.exceptions.InvalidException;
import com.hcmute.yourtours.libs.exceptions.RestException;
import com.hcmute.yourtours.libs.factory.IResponseFactory;
import com.hcmute.yourtours.libs.logging.LogContext;
import com.hcmute.yourtours.libs.logging.LogType;
import com.hcmute.yourtours.libs.model.factory.response.BaseResponse;
import com.hcmute.yourtours.models.authentication.requests.LoginRequest;
import com.hcmute.yourtours.models.authentication.requests.RefreshTokenRequest;
import com.hcmute.yourtours.models.authentication.requests.RegisterRequest;
import com.hcmute.yourtours.models.authentication.requests.VerifyOtpRequest;
import com.hcmute.yourtours.models.authentication.response.*;
import com.hcmute.yourtours.models.common.SuccessResponse;
import com.hcmute.yourtours.models.user.request.ForgotPasswordRequest;
import com.hcmute.yourtours.models.user.request.ResetPasswordWithOtpRequest;
import com.hcmute.yourtours.models.verification_token.request.ResendVerifyOtp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth/")
@Tag(name = "AUTH API: AUTH")
@Transactional
public class AuthController implements IAuthController {

    protected final IResponseFactory iResponseFactory;
    private final IAuthFactory iAuthFactory;

    public AuthController(IResponseFactory iResponseFactory, IAuthFactory iAuthFactory) {
        this.iResponseFactory = iResponseFactory;
        this.iAuthFactory = iAuthFactory;
    }


    @Override
    public ResponseEntity<BaseResponse<LoginResponse>> login(LoginRequest request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            LoginResponse response = iAuthFactory.login(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<LogoutResponse>> logout() {
        try {
            LogoutResponse response = iAuthFactory.logout();
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }


    @Override
    public ResponseEntity<BaseResponse<RefreshTokenResponse>> refreshToken(RefreshTokenRequest request, HttpServletRequest context) {
        LogContext.push(LogType.REQUEST, request);
        try {
            RefreshTokenResponse response = iAuthFactory.refreshToken(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<RegisterResponse>> registerAccount(RegisterRequest request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            RegisterResponse response = iAuthFactory.registerAccount(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<VerifyOtpResponse>> activeAccount(VerifyOtpRequest request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            VerifyOtpResponse response = iAuthFactory.activeAccount(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<SuccessResponse>> forgotPasswordRequest(ForgotPasswordRequest request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            SuccessResponse response = iAuthFactory.requestForgotPassword(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<SuccessResponse>> resetPasswordWithOtp(ResetPasswordWithOtpRequest request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            SuccessResponse response = iAuthFactory.resetPasswordWithOtp(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<SuccessResponse>> resendOtp(ResendVerifyOtp request) {
        LogContext.push(LogType.REQUEST, request);
        try {
            SuccessResponse response = iAuthFactory.resendOtp(request);
            LogContext.push(LogType.RESPONSE, response);
            return iResponseFactory.success(response);
        } catch (InvalidException e) {
            throw new RestException(e.getErrorCode());
        }
    }

}
