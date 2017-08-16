package com.example.owner.cafm;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp2 extends Activity  {

    private static final String TAG = "PhoneAuthActivity";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";



    private FirebaseAuth mAuth;

    private boolean mVerificationInProgress = false;


    private String ID;
    private String Token;
    String code;

    private Button mVerifyButton;
    private EditText SMS_Code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        mVerifyButton = (Button) findViewById(R.id.verify);
        SMS_Code = (EditText) findViewById(R.id.code);


        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        Token = getIntent().getExtras().getString("Token");


        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                code =SMS_Code.getText().toString();
                verifyPhoneNumberWithCode(ID, code);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }



    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp2.this, "signInWithCredential:success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp2.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUp2.this, "signInWithCredential:failure", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }



}