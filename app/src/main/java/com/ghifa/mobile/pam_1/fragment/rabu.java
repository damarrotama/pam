package com.ghifa.mobile.pam_1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghifa.mobile.pam_1.R;

/**
 * Created by Mobile on 12/8/2017.
 */

public class rabu extends Fragment {
    public static rabu newInstance() {
        return new rabu();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rabu, container, false);

        return view;
    }
}
