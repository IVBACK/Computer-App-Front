package com.example.computerapp.Clients;

import com.example.computerapp.Services.DesktopService;
import com.example.computerapp.Services.UserService;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getRetrofit(){

        OkHttpClient okHttpClient = getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://computerappservice.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    public static DesktopService getDesktopService(){
        DesktopService desktopService = getRetrofit().create(DesktopService.class);
        return desktopService;
    }

    public  static okhttp3.OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void
                        checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                           String authType) throws CertificateException {
                        }

                        @Override
                        public void
                        checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                           String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[]
                        getAcceptedIssuers() {
                            return new
                                    java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext =
                    SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new
                    java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting
            final SSLSocketFactory sslSocketFactory =
                    sslContext.getSocketFactory();

            okhttp3.OkHttpClient.Builder builder = new
                    okhttp3.OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory,
                    (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession
                        session) {
                    return true;
                }
            });

            okhttp3.OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
