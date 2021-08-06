package com.example.computerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.computerapp.Clients.ApiClient;
import com.example.computerapp.Models.Desktop;
import com.example.computerapp.R;
import com.example.computerapp.Responses.UserLoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textViewWelcome;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMain();
        checkWelcomeUser();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
    }

    private void checkWelcomeUser(){
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            welcomeUser();
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

    }

    private void welcomeUser(){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        String name = preferences.getString("userName","");

        String[] splittedName = name.split("\\s+");

        textViewWelcome.setText(new StringBuilder().append("Welcome ").append(splittedName[0].substring(0, 1).
                toUpperCase()).append(splittedName[0].substring(1).toLowerCase()).toString());

        welcomeTextFadeOut();
    }

    private void welcomeTextFadeOut(){

        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        textViewWelcome.startAnimation(fadeOut);
        fadeOut.setDuration(4000);
        fadeOut.setFillAfter(true);

    }

    private void initMain(){
        textViewWelcome = findViewById(R.id.textViewWelcome);
        //searchButton = findViewById(R.id.searchAcitivityButton);
    }
}