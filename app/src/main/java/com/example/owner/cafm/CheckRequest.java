package com.example.owner.cafm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CheckRequest extends Activity {

    TextView Project;
    TextView Building;
    TextView Floor;
    TextView Description;
    TextView Emergency;
    TextView Recepient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request);

        Project= (TextView) findViewById(R.id.Project_Name);
        Building= (TextView) findViewById(R.id.Building);
        Floor= (TextView) findViewById(R.id.Floor);
        Description= (TextView) findViewById(R.id.description);
        Recepient= (TextView) findViewById(R.id.Recepient);

    }
}
