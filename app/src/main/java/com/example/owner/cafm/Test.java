/*
package com.example.owner.cafm;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.owner.cafm.Interfaces.CAFMApi;
import com.example.owner.cafm.Models.OrderRequest;
import com.example.owner.cafm.Models.OrderResponse;
import com.example.owner.cafm.Models.TokenRequest;
import com.example.owner.cafm.Models.TokenResponse;
import com.example.owner.cafm.Objects.Address;
import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Test extends Activity {

    Spinner spinner_recipient;
    Spinner spinner_emergency;

    EditText Project;
    EditText Building;
    EditText Floor;
    EditText Description;

    Button Take_Picture;
    Button Upload_Image;
    Button Clear_Request;
    Button Submit;

    ImageView imageView;
    LinearLayout image;

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PHOTO = 100;

    private CAFMApi service;
    String token;
    String path="";
    String URL="No Image Available";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);


        spinner_recipient = (Spinner) findViewById(R.id.recipient);
        spinner_emergency = (Spinner) findViewById(R.id.Emergency_Level);
        Project = (EditText) findViewById(R.id.Project_Name);
        Building = (EditText) findViewById(R.id.building);
        Floor = (EditText) findViewById(R.id.floor);
        Description = (EditText) findViewById(R.id.description);
        Take_Picture = (Button) findViewById(R.id.take_picture);
        Upload_Image = (Button) findViewById(R.id.upload_image);
        Clear_Request = (Button) findViewById(R.id.clear);
        Submit = (Button) findViewById(R.id.submit);
        imageView = (ImageView) findViewById(R.id.image);
        image = (LinearLayout) findViewById(R.id.image_layout);


        Intent intent = getIntent();
        token = intent.getStringExtra("Token");


        image.setVisibility(View.GONE);

        String baseUrl = getString(R.string.requests_api);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();



        service = retrofit.create(CAFMApi.class);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Recipients, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_recipient.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Emergency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_emergency.setAdapter(adapter2);

        Take_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        Upload_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image*/
/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);

            }
        });

        Clear_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project.setText(null);
                Floor.setText(null);
                Description.setText(null);
                Building.setText(null);
                spinner_emergency.setSelection(0);
                spinner_recipient.setSelection(0);


            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner_emergency.getSelectedItemPosition()==0 && spinner_emergency.getSelectedItemPosition()==0 ){
                    Toast.makeText(Test.this, "Please enter a recipient and an emergency number", Toast.LENGTH_LONG).show();
                }
                else if (spinner_emergency.getSelectedItemPosition()==0 ) {
                    Toast.makeText(Test.this, "Please enter an emergency number", Toast.LENGTH_LONG).show();

                }
                else if (spinner_recipient.getSelectedItemPosition()==0 ) {
                    Toast.makeText(Test.this, "Please enter a recipient", Toast.LENGTH_LONG).show();

                }
                else {


                    OrderRequest tokenRequest = new OrderRequest();


                    Address address = new Address(Building.getText().toString(), Project.getText().toString(), "1", Floor.getText().toString() );
                    tokenRequest.setType(1);
                    tokenRequest.setDescription(Description.getText().toString());
                    tokenRequest.setEmergency(spinner_emergency.getSelectedItemPosition());
                    tokenRequest.setPhoto("url");
                    tokenRequest.setAddress(address);


                    Call<OrderResponse> tokenResponseCall = service.getOrderAccess("Bearer "+ token, tokenRequest);

                    tokenResponseCall.enqueue(new Callback<OrderResponse>() {
                        @Override
                        public void onResponse(Response<OrderResponse> response, Retrofit retrofit) {

                            int statusCode = response.code();
                            if (response.code() == 401 || response.code()==400) {

                                Toast.makeText(Test.this, "Error ! "+response.message(), Toast.LENGTH_SHORT).show();


                            }
                            else if (response.code()==200){
                                Toast.makeText(Test.this, "Success", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Throwable t) {

                            Toast.makeText(Test.this, "Failure", Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "Failure:" +t.getMessage());

                        }
                    });
                }

            }
        });
    }

*/
/*    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            imageView.setVisibility(View.VISIBLE);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        else if (requestCode == SELECT_PHOTO) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
                path = getPathFromURI(selectedImageUri);
                image.setVisibility(View.VISIBLE);

               URL = getBase64String(selectedImageUri);

        }

    }


    private String getBase64String(Uri uri) {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);

    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }*//*



}
*/




