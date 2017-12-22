package com.ghifa.mobile.pam_1.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghifa.mobile.pam_1.R;
import com.ghifa.mobile.pam_1.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mobile on 12/13/2017.
 */

public class dataadapter extends RecyclerView.Adapter<dataadapter.ViewHolder> {
    private ArrayList<androidversion> android_versions;
    private Context context;
    RelativeLayout rl_jenis;

    public dataadapter(Context context,ArrayList<androidversion> android_versions) {
        this.context = context;
        this.android_versions = android_versions;

    }

    @Override
    public dataadapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android_versions.get(i).getAndroid_version_name());
        Picasso.with(context).load(android_versions.get(i).getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_android;
        ImageView img_android;


        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("RecyclerView", "onClick：" + getItemId());
                }
            });

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);

        }

    }


}
