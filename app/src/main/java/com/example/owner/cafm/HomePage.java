package com.example.owner.cafm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.cafm.R;

public class HomePage extends Activity {

    ImageView MakeRequest;
    ImageView ViewRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        MakeRequest = (ImageView) findViewById(R.id.make_a_request);
        ViewRequest = (ImageView) findViewById(R.id.review);

        MakeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent token = getIntent();
                Intent intent = new Intent(HomePage.this, MakeRequest.class);
                intent.putExtra("Token", token.getStringExtra("Token"));
                startActivity(intent);
            }
        });
        ViewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent token = getIntent();
                Intent intent = new Intent(HomePage.this, CheckRequest.class);
                intent.putExtra("Token", token.getStringExtra("Token"));
                startActivity(intent);
            }
        });
    }
}
