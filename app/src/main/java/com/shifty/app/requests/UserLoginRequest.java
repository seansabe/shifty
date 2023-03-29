package com.shifty.app.requests;

public class UserLoginRequest {
    private String email;

    private String password;

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
