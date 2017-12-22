package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtName,edtNim,edtAlamat,edtTelepon,edtEmail;
    ImageView imgView;
    RadioButton rdoMale,rdoFemale;
    Button btnSend,btnCallNumber;
    String genderType = "";
    int CAMERA_PIC_REQUEST = 99;
    Bitmap bitmap; // your bitmap
    private String selectedImagePath;
    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName         = (EditText) findViewById(R.id.nama);
        edtNim          = (EditText) findViewById(R.id.nim);
        edtAlamat       = (EditText) findViewById(R.id.alamat);
        edtTelepon      = (EditText) findViewById(R.id.no_telp);
        edtEmail        = (EditText) findViewById(R.id.email);
        btnSend         = (Button) findViewById(R.id.btnSend);
        btnCallNumber   = (Button) findViewById(R.id.btnCallNumber);
        imgView         = (ImageView) findViewById(R.id.imgView);

        btnSend.setOnClickListener(MainActivity.this);
        imgView.setOnClickListener(MainActivity.this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSend:

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("NAME",edtName.getText().toString());
                intent.putExtra("NIM",edtNim.getText().toString());
                intent.putExtra("ALAMAT",edtAlamat.getText().toString());
                intent.putExtra("TELEPON",edtTelepon.getText().toString());
                intent.putExtra("EMAIL",edtEmail.getText().toString());

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);

                byte[] byteArray = bs.toByteArray();
                intent.putExtra("PICTURE", byteArray);
                startActivity(intent);
                break;
            case R.id.imgView:

                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CAMERA_PIC_REQUEST);

                break;
            case R.id.btnCallNumber:

                Intent call = new Intent(MainActivity.this,CallNumberActivity.class);
                startActivity(call);

                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CAMERA_PIC_REQUEST) {

            Uri uri = data.getData();
            //Log.d("bitmap", String.valueOf(bitmap));

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                Log.d("bitmap", String.valueOf(bitmap));
                imgView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
