package com.ghifa.mobile.pam_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CallNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_number);

        Button dial=(Button)findViewById(R.id.call);
        Button dial2=(Button)findViewById(R.id.call2);
        Button dial3=(Button)findViewById(R.id.call3);

        dial.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String toDial ="tel:"+"087739249648";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
            }
        });

        dial2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String toDial ="tel:"+"085643678987";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
            }
        });

        dial3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String toDial ="tel:"+"081809987765";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
            }
        });
    }

}
