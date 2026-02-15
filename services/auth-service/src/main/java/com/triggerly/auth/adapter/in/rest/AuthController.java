package com.triggerly.auth.adapter.in.rest;

import com.triggerly.auth.application.dto.ChangePasswordRequest;
import com.triggerly.auth.application.dto.LoginRequest;
import com.triggerly.auth.application.dto.LoginResponse;
import com.triggerly.auth.application.dto.RegisterRequest;
import com.triggerly.auth.application.dto.RegisterResponse;
import com.triggerly.auth.application.dto.UserInfoResponse;
import com.triggerly.auth.application.port.in.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import triggerly.common.response.ServiceResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase,
            ChangePasswordUseCase changePasswordUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserInfoUseCase getUserInfoUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ServiceResponse<RegisterResponse>> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = registerUserUseCase.execute(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ServiceResponse.success(response, HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<ServiceResponse<LoginResponse>> login(
            @RequestBody LoginRequest request,
            HttpServletRequest servletRequest
    ) {
        String ipAddress = extractIpAddress(servletRequest);
        LoginResponse response = loginUserUseCase.execute(request, ipAddress);
        return ResponseEntity.ok(ServiceResponse.success(response, HttpStatus.OK.value()));
    }

    @GetMapping("/me")
    public ResponseEntity<ServiceResponse<UserInfoResponse>> getUserInfo(
            @RequestAttribute("userId") String userId
    ) {
        UserInfoResponse response = getUserInfoUseCase.execute(userId);
        return ResponseEntity.ok(ServiceResponse.success(response, HttpStatus.OK.value()));
    }

    @PutMapping("/password")
    public ResponseEntity<ServiceResponse<Void>> changePassword(
            @RequestAttribute("userId") String userId,
            @RequestBody ChangePasswordRequest request
    ) {
        changePasswordUseCase.execute(userId, request);
        return ResponseEntity.ok(ServiceResponse.success(null, HttpStatus.OK.value()));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ServiceResponse<Void>> deleteUser(
            @RequestAttribute("userId") String userId
    ) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.ok(ServiceResponse.success(null, HttpStatus.OK.value()));
    }

    private String extractIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}

