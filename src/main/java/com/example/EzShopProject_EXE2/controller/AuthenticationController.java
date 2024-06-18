package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.response.AuthenticationResponse;
import com.example.EzShopProject_EXE2.response.UpdateUserRequest;
import com.example.EzShopProject_EXE2.service.impl.AuthenticationService;
import com.example.EzShopProject_EXE2.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
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

    @GetMapping("/user")
    public ResponseEntity<User> getUserByToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        User user = authenticationService.getByUser(token);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateUserRequest updateUserRequest
    ) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        User user = authenticationService.getByUser(token);

        User updatedUser = authenticationService.updateUser(
                user.getId(),
                isNullOrEmpty(updateUserRequest.getFirstName()) ? user.getFirstName() : updateUserRequest.getFirstName(),
                isNullOrEmpty(updateUserRequest.getLastName()) ? user.getLastName() : updateUserRequest.getLastName(),
                isNullOrEmpty(updateUserRequest.getEmail()) ? user.getEmail() : updateUserRequest.getEmail(),
                isNullOrEmpty(updateUserRequest.getPhone()) ? user.getPhone() : updateUserRequest.getPhone()
        );

        return ResponseEntity.ok(updatedUser);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

}