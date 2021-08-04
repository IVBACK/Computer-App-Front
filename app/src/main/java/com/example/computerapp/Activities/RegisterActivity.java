package com.example.computerapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.computerapp.Clients.ApiClient;
import com.example.computerapp.R;
import com.example.computerapp.Requests.UserRegisterRequest;
import com.example.computerapp.Responses.UserRegisterResponse;
import com.example.computerapp.Validations.UserValidation;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button rgsButton;
    EditText edName, edMail, edPassword, edConfirmPassword;
    ProgressDialog progressDialog;
    UserValidation userValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userValidation = new UserValidation();

        initRegister();

        rgsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edName.getText().toString()) || TextUtils.isEmpty(edMail.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())){
                    String message = "Fields Cannot Be Empty";
                    Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
                }else if(!edPassword.getText().toString().equals(edConfirmPassword.getText().toString())){
                    String message = "Passwords Are Not Matching";
                    Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
                }else{
                    checkAreFieldsValid();
                }
            }
        });
    }

    private void initRegister(){
        rgsButton = findViewById(R.id.rgsButton);
        edName = findViewById(R.id.editTextTextPersonNameRegister);
        edMail = findViewById(R.id.editTextTextEmailAddressRegister);
        edPassword = findViewById(R.id.editTextTextPasswordRegister);
        edConfirmPassword = findViewById(R.id.editTextTextConfirmPasswordRegister);
    }

    private void checkAreFieldsValid(){
        if(!userValidation.isValidName(edName.getText().toString())){
            String message = "Incorrect Name Format";
            Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
            return;

        }
        else if(!userValidation.isValidMail(edMail.getText().toString())){
            String message = "Incorrect Mail Format";
            Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
        }
        else{
            createUserRegisterRequest();
        }
    }

    private void createUserRegisterRequest(){

        setAndShowProgressDialog();

        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setEmail(edMail.getText().toString());
        registerRequest.setName(edName.getText().toString());
        registerRequest.setPassword(edPassword.getText().toString());

        handleSSLHandshake();
        registerUser(registerRequest);
    }


    private void registerUser(UserRegisterRequest userRegisterRequest){
        Call<UserRegisterResponse> registerResponseCall = ApiClient.getUserService().registerUser(userRegisterRequest);
        registerResponseCall.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if(response.isSuccessful()){

                    String message = "Successful";
                    Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();

                }else{

                    String message = null;
                    try {
                        message = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.d("TAG", "onResponseIsNotSuccessful: " + message);
                    Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new
                    X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[]
                                                               certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[]
                                                               certs, String authType) {
                        }
                    }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());

            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }});
        } catch (Exception ignored) {
        }
    }

    private void setAndShowProgressDialog() {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}