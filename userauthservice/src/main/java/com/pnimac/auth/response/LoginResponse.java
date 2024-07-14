package com.pnimac.auth.response;

public class LoginResponse {

    private final String token;
    private final String username;

    public LoginResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}


