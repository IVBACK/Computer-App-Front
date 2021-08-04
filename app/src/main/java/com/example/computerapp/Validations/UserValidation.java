package com.example.computerapp.Validations;

import android.util.Patterns;

import java.util.regex.Pattern;

public class UserValidation {
    public boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    public boolean isValidMail(String mail) {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }
}
