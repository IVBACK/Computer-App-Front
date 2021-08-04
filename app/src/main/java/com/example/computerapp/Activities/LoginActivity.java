package com.example.computerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computerapp.Clients.ApiClient;
import com.example.computerapp.R;
import com.example.computerapp.Requests.UserLoginRequest;
import com.example.computerapp.Responses.UserLoginResponse;
import com.example.computerapp.Validations.UserValidation;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button lgnButton;
    EditText edMail, edPassword;
    TextView tvSignUp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        initLogin();
        tvSignUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        lgnButton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(edMail.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())){

                String message = "Fields Cannot Be Empty";
                Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();

            }else{
                checkAreFieldsValid();
            }
        });
    }

    private void initLogin(){
        lgnButton = findViewById(R.id.lgnButton);
        edMail = findViewById(R.id.editTextTextEmailAddress);
        edPassword = findViewById(R.id.editTextTextPassword);
        tvSignUp = findViewById(R.id.textViewSignUp);
    }

    private void checkAreFieldsValid(){
        UserValidation userValidation = new UserValidation();
        if(!userValidation.isValidMail(edMail.getText().toString())){
            String message = "Incorrect Mail Format";
            Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
        }else{
            createUserLoginRequest();
        }
    }

    private void createUserLoginRequest(){

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(edMail.getText().toString());
        userLoginRequest.setPassword(edPassword.getText().toString());

        setAndShowProgressDialog();
        loginUser(userLoginRequest);

    }

    private void loginUser(UserLoginRequest userLoginRequest){
        Call<UserLoginResponse> userLoginResponseCall = ApiClient.getUserService().loginUser(userLoginRequest);
        userLoginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if(response.isSuccessful()){

                    UserLoginResponse userLoginResponse = response.body();
                    Log.e("TAG", "ResponseBody: " + userLoginResponse);

                    progressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("data",userLoginResponse));
                    finish();

                }else{
                    Log.d("here00, ", userLoginRequest.getEmail());
                    String message = null;
                    try {
                        message = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.d("TAG", "onResponseIsNotSuccessful: " + message);
                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, "OnFailure" + message,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void setAndShowProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}