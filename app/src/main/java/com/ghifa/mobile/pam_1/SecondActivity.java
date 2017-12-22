package com.ghifa.mobile.pam_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Context ctx ;
    TextView txtName,txtNim,txtAlamat,txtTelepon,txtEmail;
    ImageView imgViewResult;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ctx = this;
        setContentView(R.layout.activity_second);

        txtName             = (TextView) findViewById(R.id.txtName);
        txtNim              = (TextView) findViewById(R.id.txtNim);
        txtAlamat           = (TextView) findViewById(R.id.txtAlamat);
        txtTelepon          = (TextView) findViewById(R.id.txtTelepon);
        txtEmail            = (TextView) findViewById(R.id.txtEmail);
        imgViewResult       = (ImageView) findViewById(R.id.imgViewResult);

        Bundle extras       = getIntent().getExtras();
        String Name         = extras.getString("NAME");
        String Nim          = extras.getString("NIM");
        String Alamat       = extras.getString("ALAMAT");
        String Telepon      = extras.getString("TELEPON");
        String Email        = extras.getString("EMAIL");

        txtName.setText(Name);
        txtNim.setText(Nim);
        txtAlamat.setText(Alamat);
        txtTelepon.setText(Telepon);
        txtEmail.setText(Email);

        byte[] byteArray = extras.getByteArray("PICTURE");
        bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        imgViewResult.setImageBitmap(bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
