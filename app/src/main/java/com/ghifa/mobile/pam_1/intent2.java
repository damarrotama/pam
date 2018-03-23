package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class intent2 extends AppCompatActivity {

    String nim, nama, gender, bola, renang, basket;
    TextView txtNim, txtNama, txtGender, txtBola, txtRenang, txtBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        txtNim      = (TextView) findViewById(R.id.textViewNim);
        txtNama     = (TextView) findViewById(R.id.textViewNama);
        txtGender   = (TextView) findViewById(R.id.textViewGender);
        txtBola     = (TextView) findViewById(R.id.textViewBola);
        txtRenang   = (TextView) findViewById(R.id.textViewRenang);
        txtBasket   = (TextView) findViewById(R.id.textViewBasket);

        Intent i    = getIntent();
        nim         = i.getStringExtra("nimnya");
        nama        = i.getStringExtra("namanya");
        gender      = i.getStringExtra("gendernya");
        bola        = i.getStringExtra("bolanya");
        renang      = i.getStringExtra("renangnya");
        basket      = i.getStringExtra("basketnya");

        String kirim = bola+"-"+renang+"-"+basket;
        Log.d("data", kirim);

        txtNim.setText(nim);
        txtNama.setText(nama);
        txtGender.setText(gender);

        if(bola.equals("0")){
            txtBola.setText(" ");
        }else{
            txtBasket.setText(basket);
        }
        if(renang.equals("0")){
            txtRenang.setText(" ");
        }else{
            txtRenang.setText(renang);
        }
        if(basket.equals("0")){
            txtBasket.setText(" ");
        }else{
            txtBasket.setText(basket);
        }

        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intent2.this, intent1.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(intent2.this, intent3.class);
                startActivity(intent);
            }
        });

    }
}
