package com.ghifa.mobile.pam_1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ghifa.mobile.pam_1.component.BottomNavigationViewBehavior;
import com.ghifa.mobile.pam_1.component.BottomNavigationViewHelper;
import com.ghifa.mobile.pam_1.fragment.home;
import com.ghifa.mobile.pam_1.fragment.lokasi;
import com.ghifa.mobile.pam_1.fragment.pengumuman;
import com.ghifa.mobile.pam_1.fragment.rabu;
import com.ghifa.mobile.pam_1.fragment.selasa;
import com.ghifa.mobile.pam_1.fragment.senin;

public class MainBotomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_botom);

        //mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent, pengumuman.newInstance())
                    .commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = pengumuman.newInstance();
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(0);
                }
                break;
            case R.id.navigation_dashboard:
                fragment = lokasi.newInstance();
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(8);
                }
                break;
            case R.id.navigation_notifications:
                fragment = rabu.newInstance();
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setElevation(8);
                }
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();

        return true;
    }

}
