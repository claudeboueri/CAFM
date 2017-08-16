package com.example.owner.cafm;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.cafm.Interfaces.CAFMApi;
import com.example.owner.cafm.Models.OrderRequest;
import com.example.owner.cafm.Models.OrderResponse;
import com.example.owner.cafm.Models.RequestResponse;
import com.example.owner.cafm.Objects.Address;
import com.example.owner.cafm.Objects.Request;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.R.id.input;

public class CheckRequest extends FragmentActivity {

    LinearLayout fragment;
    Spinner spinner;
    TextView Project_Name;
    TextView Building;
    TextView Floor;
    TextView Description;
    TextView Emergency;
    TextView Recepient;
    ImageView Image;



    private CAFMApi service;
    Request[] requests;
    String[] requests_title;
    String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request);

          spinner = (Spinner) findViewById(R.id.spinner);
          fragment = (LinearLayout) findViewById(R.id.fragment);
          Project_Name=  (TextView) findViewById(R.id.Project_Name);
          Building=  (TextView) findViewById(R.id.Building);
          Floor =  (TextView) findViewById(R.id.Floor);
          Description= (TextView) findViewById(R.id.description);
          Emergency= (TextView) findViewById(R.id.Emergency);
          Recepient= (TextView) findViewById(R.id.Recepient);
        Image = (ImageView) findViewById(R.id.image);




        fragment.setVisibility(View.GONE);

        Intent intent = getIntent();
        token = intent.getStringExtra("Token");

        String baseUrl = getString(R.string.requests_api);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CAFMApi.class);



        Call<RequestResponse> tokenResponseCall = service.getRequests("Bearer " + token);

        tokenResponseCall.enqueue(new Callback<RequestResponse>() {
            @Override
            public void onResponse(Response<RequestResponse> response, Retrofit retrofit) {

                int statusCode = response.code();
                if (response.code() == 401 || response.code() == 400) {

                    Toast.makeText(CheckRequest.this, "Error ! " + response.message(), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 200) {
                    requests= response.body().getRequests();
                    Toast.makeText(CheckRequest.this, "Success", Toast.LENGTH_SHORT).show();
                    requests_title = new String[requests.length];

                    for (int i=0; i<requests.length; i++){
                        requests_title[i]="request " +i;
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(CheckRequest.this, android.R.layout.simple_spinner_item, requests_title);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    spinner.setAdapter(spinnerArrayAdapter);

                }

            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(CheckRequest.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "Failure:" + t.getMessage());

            }
        });




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spinner.getSelectedItemPosition();

                if (item==0){
                    fragment.setVisibility(View.GONE);
                }
                else {
                    fragment.setVisibility(View.VISIBLE);
                    Request request = requests[item];
                    Project_Name.setText(requests_title[item]+"");
                   /* String building = request.getAddress().getBuilding()+"";
                    Building.setText(building);*/
                   /* Floor.setText(request.getAddress().getFloor()+"");
                    Recepient.setText(request.getType()+"");
                    Description.setText(request.getDescription()+"");
                    Emergency.setText(request.getEmergency()+"");*/
                  /* if (request.getPhoto()!=""){
                        Image.setImageBitmap(DecodeImage(request.getPhoto()));
                    }*/


                }

            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
    }

    public Bitmap DecodeImage(String Image){
        byte[] decodedBytes = Base64.decode(Image, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}

