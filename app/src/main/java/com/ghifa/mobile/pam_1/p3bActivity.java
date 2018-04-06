package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class p3bActivity extends AppCompatActivity {

    EditText editTextNim, editNama;
    String text;

    private Spinner spNamen2;
    private String[] germanFeminine = {
            "Teknik Informatika",
            "Sistem Informasi",
            "Teknik Komputer",
            "Komputerisasi Akuntansi",
            "Manajemen Informatika"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3b);

        editTextNim         = (EditText) findViewById(R.id.nim);
        editNama            = (EditText) findViewById(R.id.nama);

        spNamen2            = (Spinner) findViewById(R.id.sp_jurusan);
        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, germanFeminine);

        // mengeset Array Adapter tersebut ke Spinner
        spNamen2.setAdapter(adapter);



        // mengeset listener untuk mengetahui saat item dipilih
        spNamen2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                //Toast.makeText(p3bActivity.this, "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                text = adapterView.getSelectedItem().toString();

                Log.d("tesss", text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Button saveBtn = (Button) findViewById(R.id.button);
        saveBtn .setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                System.out.println("Selected cardStatusString : " + text ); //this will print the result

                    final String nim    = editTextNim.getText().toString().trim();
                    final String nama   = editNama.getText().toString().trim();

                    Intent utama = new Intent(p3bActivity.this, p3cActivity.class);
                    utama.putExtra("nimnya", nim);
                    utama.putExtra("namanya", nama);
                    utama.putExtra("jurusannya", text);
                    startActivity(utama);
                    finish();

            }
        });
    }

}
