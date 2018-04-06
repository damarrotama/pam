package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class P3aActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3a);
    }

    public void Login(View arg0) {
        Intent utama = new Intent(P3aActivity.this, p3bActivity.class);
        startActivity(utama);
        finish();
    }
}
