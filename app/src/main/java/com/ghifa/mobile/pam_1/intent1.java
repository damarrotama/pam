package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class intent1 extends AppCompatActivity {

    EditText editTextNim, editNama, editTextPassword;
    RadioGroup radioGroupGender;
    ProgressBar progressBar;
    CheckBox sepakbola, renang, basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        editTextNim         = (EditText) findViewById(R.id.editTextNim);
        editNama            = (EditText) findViewById(R.id.editNama);
        radioGroupGender    = (RadioGroup) findViewById(R.id.radioGender);
        sepakbola           = (CheckBox)findViewById(R.id.chkSepakbola);
        renang              = (CheckBox)findViewById(R.id.chkRenang);
        basket              = (CheckBox)findViewById(R.id.chkBasket);


        findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bolak, renangk, basketk;

                if(sepakbola.isChecked()){
                    bolak = "Sepak Bola";
                }else{
                    bolak = "0";
                }
                if(renang.isChecked()){
                    renangk = "Renang";
                }else{
                    renangk = "0";
                }
                if(basket.isChecked()){
                    basketk = "Basket";
                }else{
                    basketk = "0";
                }

                String kirim = sepakbola+"-"+renang+"-"+basket;
                //Log.d("kirim", result);

                senddata(bolak,renangk,basketk);

            }
        });
    }
    private void senddata(String bolak, String renangk, String basketk) {
        final String nim = editTextNim.getText().toString().trim();
        final String nama = editNama.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //checkbox

        final String bolas   = sepakbola.getText().toString().trim();
        final String renangs = renang.getText().toString().trim();
        final String baskets = basket.getText().toString().trim();

        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(intent1.this, intent2.class);
        intent.putExtra("nimnya", nim);
        intent.putExtra("namanya", nama);
        intent.putExtra("gendernya", gender);
        intent.putExtra("bolanya", bolak);
        intent.putExtra("renangnya", renangk);
        intent.putExtra("basketnya", basketk);
        startActivity(intent);

    }

}
