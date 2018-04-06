package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class p3cActivity extends AppCompatActivity {

    String nim, nama, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3c);

        Intent i    = getIntent();
        nim         = i.getStringExtra("nimnya");
        nama        = i.getStringExtra("namanya");
        jurusan     = i.getStringExtra("jurusannya");

        String kirim = nim+"-"+nama+"-"+jurusan;
        Log.d("data", kirim);

        String[] countryArray = {nim, nama, jurusan};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview,R.id.label, countryArray);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

    }

}
