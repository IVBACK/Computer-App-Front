package com.example.computerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    UserLoginResponse userLoginResponse;
    TextView textViewWelcome;
    List<Desktop> desktops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMain();

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            userLoginResponse = (UserLoginResponse) intent.getSerializableExtra("data");

            String[] splittedName = userLoginResponse.name.split("\\s+");

            textViewWelcome.setText(new StringBuilder().append("Welcome ").append(splittedName[0].substring(0, 1).
                    toUpperCase()).append(splittedName[0].substring(1).toLowerCase()).toString());

            //getDesktops();
        }
    }

    private void getDesktops(){
        Call<List<Desktop>>  desktopCall = ApiClient.getDesktopService().getDesktops();
        desktopCall.enqueue(new Callback<List<Desktop>>() {
            @Override
            public void onResponse(Call<List<Desktop>> call, Response<List<Desktop>> response) {
                if (response.isSuccessful()){

                    String message = "Response Successful";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                    if (response.body() != null);{
                        desktops.addAll(response.body());
                    }

                }else{
                    String message = "Response Is Not Successful";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Desktop>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(MainActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initMain(){
        textViewWelcome = findViewById(R.id.textViewWelcome);
    }
}