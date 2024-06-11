package com.example.EzShopProject_EXE2.response;

import com.example.EzShopProject_EXE2.model.User;

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
