package com.safari.todo.controller;

import com.safari.todo.config.aspect.MethodDurationLog;
import com.safari.todo.config.security.JwtTokenUtil;
import com.safari.todo.dto.AuthRequestDto;
import com.safari.todo.dto.AuthResponseDto;
import com.safari.todo.model.User;
import com.safari.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("login")
    @MethodDurationLog
    public ResponseEntity<Object> login(@RequestBody @Valid AuthRequestDto request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.accepted().body(jwtTokenUtil.generateAccessToken(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
