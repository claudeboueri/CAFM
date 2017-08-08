package com.example.owner.cafm;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends Activity {


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

        login.setText("Clicked");

        if (username.getText().length()==0 && password.getText().length()==0) {
            Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        } else if (username.getText().length()==0) {
            Toast.makeText(MainActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
        } else if (password.getText().length()==0) {
            Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else {
            user = username.getText().toString().replaceAll("\\s+$", "");
            pass = password.getText().toString();



            TokenRequest tokenRequest = new TokenRequest();


            tokenRequest.setUsername(user);
            tokenRequest.setPassword(pass);
            tokenRequest.setNumber(71412978);
            tokenRequest.setType(1);


           Call<TokenResponse> tokenResponseCall = service.getTokenAccess(tokenRequest);

            tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Response<TokenResponse> response, Retrofit retrofit) {

                    int statusCode = response.code();
                    if (response.code() == 401 ) {

                        Toast.makeText(MainActivity.this, "Incorrect Password or Username", Toast.LENGTH_SHORT).show();
                    } else if (response.code()==400) {

                        Toast.makeText(MainActivity.this, "Error ! "+ response.message(), Toast.LENGTH_SHORT).show();

                    }
                    else if (response.code()==200){
                        Intent intent = new Intent (MainActivity.this, HomePage.class);
                        TokenResponse tokenResponse = response.body();
                        intent.putExtra("Token", tokenResponse.getToken());
                        intent.putExtra("User", tokenResponse.getUser());
                        startActivity(intent);

                    }

                }

                @Override
                public void onFailure(Throwable t) {

                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", "Failure:" +t.getMessage());

                }
            });
        }


}


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



}

