package com.example.owner.cafm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owner.cafm.Interfaces.CAFMApi;
import com.example.owner.cafm.Models.TokenRequest;
import com.example.owner.cafm.Models.TokenResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;
    private Button login;
    String user;
    String pass;


    private CAFMApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }

        });


        String baseUrl = getString(R.string.login_api);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CAFMApi.class);

    }


    public void Login() {

        if (username.getText().equals("") && password.getText().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter username and passord", Toast.LENGTH_SHORT).show();
        } else if (username.getText().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
        } else if (password.getText().equals("")) {
            Toast.makeText(MainActivity.this, "password", Toast.LENGTH_SHORT).show();
        } else {
            user = username.getText().toString().replaceAll("\\s+$", "");
            pass = password.getText().toString();

            TokenRequest tokenRequest = new TokenRequest();


            // "111" is a test case user should be changed to string -- backend
            tokenRequest.setNumber(111);
            tokenRequest.setPassword(pass);
            tokenRequest.setNumber(1);


           Call<TokenResponse> tokenResponseCall = service.getTokenAccess(tokenRequest);

            tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Response<TokenResponse> response, Retrofit retrofit) {

                    int statusCode = response.code();

                    TokenResponse tokenResponse = response.body();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Throwable t) {

                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", "Failure:" +t.getMessage());

                }
            });
        }
    }
}
