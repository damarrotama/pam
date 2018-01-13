package com.ghifa.mobile.pam_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class LokasiActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);

        url = "https://www.google.co.id/maps/place/Parangtritis+Beach/@-8.0255145,110.3199843,15z/data=!3m1!4b1!4m5!3m4!1s0x2e7b00975eac533d:0x351bfe1453e22e36!8m2!3d-8.0251022!4d110.3295648?hl=en";  //Pendefinisian URL

        WebView view = (WebView) this.findViewById(R.id.webView);  //sinkronisasi object berdasarkan id
        view.getSettings().setJavaScriptEnabled(true);  //untuk mengaktifkan javascript
        view.loadUrl(url);   //agar URL terload saat dibuka aplikasi
    }
}
