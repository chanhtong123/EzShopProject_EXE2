package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.response.AuthenticationResponse;
import com.example.EzShopProject_EXE2.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
