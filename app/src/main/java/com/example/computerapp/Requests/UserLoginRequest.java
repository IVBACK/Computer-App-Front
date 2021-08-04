package com.example.computerapp.Requests;

public class UserLoginRequest {

    private String Email;
    private String Password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
