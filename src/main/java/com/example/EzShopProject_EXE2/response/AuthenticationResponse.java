package com.example.EzShopProject_EXE2.response;

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
