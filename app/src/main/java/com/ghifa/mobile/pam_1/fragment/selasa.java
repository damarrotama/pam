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

public class selasa extends Fragment {
    public static selasa newInstance() {
        return new selasa();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selasa, container, false);

        return view;
    }
}
