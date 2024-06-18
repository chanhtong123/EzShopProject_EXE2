package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.repository.CartRepository;
import com.example.EzShopProject_EXE2.repository.UserRepository;
import com.example.EzShopProject_EXE2.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CartRepository cartRepository;


    public AuthenticationResponse register(User request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUsername());
        user.setPassWord(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setStatus(request.isStatus());


        user.setRole(request.getRole());

        user = repository.save(user);

        String token = jwtService.generateToken(user);

        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setCreatedAt(new Date());
        cartRepository.save(newCart);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUserName(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public User getByUser(String token) {
        String username = jwtService.extractUserName(token);
        return repository.findByUserName(username).orElseThrow();
    }


    public User updateUser(Long userId, String firstName, String lastName, String email, String phone) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (phone != null) {
            user.setPhone(phone);
        }

        user.setModifiedDate(new Date());

        return repository.save(user);
    }
}