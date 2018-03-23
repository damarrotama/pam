package com.ghifa.mobile.pam_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.ghifa.mobile.pam_1.component.ControllerUrl;
import com.ghifa.mobile.pam_1.component.DividerItemDecoration;
import com.ghifa.mobile.pam_1.http.HttpClient;
import com.ghifa.mobile.pam_1.model.Wisata;
import com.ghifa.mobile.pam_1.sqlite.Sqlite;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ListWisataActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    String id_pengumuman;
    private ControllerUrl link_url;
    private SharedPreferences session;
    // private String EMAILNYA = "";
    private Sqlite dbhelper;
    private String nomor_va, kode_klien, auto_debet;
    private Wisata susunanData;
    private vaAdapter arrayAdapter;
    private JSONArray semuaWisata = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout ll_not_found,ll_data;
    private LinearLayout detail_pembayaran, btnDetailBayar, btnBayar;
    private String total_tagihan;
    private int total_bayar_tmp, count_bayar_tmp;
    private ProgressBar progressBar;
    private TextView tvTotalTagihan, tvTotalBayarTmp, detailButton;
    private RecyclerView recyclerView;
    private boolean status_loading = false;
    private static ArrayList<Wisata> dataList = new ArrayList<>();
    private Context context;
    int PosLast = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = ListWisataActivity.this;

        id_pengumuman   = getIntent().getStringExtra("id_pengumuman");
        link_url        = new ControllerUrl(this);
        dbhelper        = new Sqlite(this);

        progressBar         = (ProgressBar) findViewById(R.id.progressBar);

        ll_data             = (LinearLayout) findViewById(R.id.ll_data);
        ll_not_found        = (LinearLayout) findViewById(R.id.ll_not_found);
        progressBar         = (ProgressBar) findViewById(R.id.progressBar);

        // 1. get a reference to recyclerView
        recyclerView        = (RecyclerView) findViewById(R.id.list);
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // 3. create an adapter
        arrayAdapter        = new vaAdapter(dataList);
        // 4. set adapter
        recyclerView.setAdapter(arrayAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                jalankan_asintaxt();
            }
        });
    }


    public void TampilRefreshProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void SembunyikanRefreshProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(status_loading) {
            tampildata_va();
        }
    }

    public void onRefresh() {
        jalankan_asintaxt();
    }

    public void jalankan_asintaxt() {
        new GetTagihan().execute();
    }

    class GetTagihan extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //dataList.clear();

            TampilRefreshProgress();

        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            int jikaSukses;
            boolean data;

            String tb_ = "WISATA";
            dbhelper.hapus(tb_);
            dataList.clear();

            try {

                FormBody.Builder formBody = new FormBody.Builder();

                String idwisata = id_pengumuman;

                //Log.e("idpengguna", idpengguna);

                formBody.add("email", "-");
                formBody.add("idwisata", idwisata);

                RequestBody body = formBody.build();

                HttpClient client = new HttpClient();
                String postResponse = client.doPostBody(link_url.getURLBerita(), body);

                Log.d("onSuccess", postResponse);

                JSONObject response = new JSONObject(postResponse);

                jikaSukses = response.getInt(link_url.TAG_SUKSES);

                if (jikaSukses == 1) {

                    semuaWisata = response.getJSONArray("wisata");

                    for (int i = 0; i < semuaWisata.length(); i++) {

                        JSONObject Data = semuaWisata.getJSONObject(i);

                        dbhelper.InsertWisata(
                                Data.getString("id"),
                                Data.getString("judul"),
                                Data.getString("isi"),
                                Data.getString("likes"),
                                Data.getString("gambar"),
                                Data.getString("parent")
                        );

                    }

                    if (semuaWisata.length() > 0) {
                        Log.d("status sqlite", "bisa simpan sqlite karna data lebih dari 0 uuuu");
                        data = true;
                    } else {
                        Log.d("status sqlite", "gagal simpan sqlite karna data kosong");
                        data = false;
                    }

                } else {
                    data = false;
                    Log.d("status sqlite", "gagal simpan sqlite");
                }

            } catch (IOException e) {
                data = false;
                Log.d("IOException", e.toString());
            } catch (JSONException e) {
                data = false;
                Log.d("JSONException", e.toString());
            }
            return data;
        }

        protected void onPostExecute(Boolean sukses) {
            super.onPostExecute(sukses);

            status_loading = true;

            if (sukses) {

                Log.d("pesan dari php", "Data ketemu sukses :)");

                if(ll_not_found.getVisibility()!=View.GONE) {
                    TranslateAnimation animate_ll_not_found = new TranslateAnimation(0, ll_not_found.getHeight(), 0, 0);
                    animate_ll_not_found.setDuration(1000);
                    animate_ll_not_found.setFillAfter(true);

                    ll_not_found.startAnimation(animate_ll_not_found);

                    ll_not_found.setVisibility(View.GONE);
                }
                ll_data.setVisibility(View.VISIBLE);

            } else {
                Log.d("pesan dari php", "Data tidak ditemukan");

                TranslateAnimation animate_ll_not_found = new TranslateAnimation(ll_not_found.getHeight(),0,0,0);
                animate_ll_not_found.setDuration(1000);
                animate_ll_not_found.setFillAfter(true);

                ll_not_found.startAnimation(animate_ll_not_found);

                ll_not_found.setVisibility(View.VISIBLE);
            }

            tampildata_va();

            progressBar.setVisibility(View.INVISIBLE);
            SembunyikanRefreshProgress();
        }
    }

    public void tampildata_va() {

        dataList.clear();

        Cursor tampilData = dbhelper.query("SELECT * FROM WISATA");
        try {
            while (tampilData.moveToNext()) {
                susunanData = new Wisata(
                        tampilData.getString(1),
                        tampilData.getString(2),
                        tampilData.getString(3),
                        tampilData.getString(4),
                        tampilData.getString(5),
                        tampilData.getString(6)
                );

                dataList.add(susunanData);
            }
        } finally {
            if (tampilData != null && !tampilData.isClosed())
                tampilData.close();
        }

        arrayAdapter.notifyDataSetChanged();
    }

    private class vaAdapter extends RecyclerView.Adapter<vaAdapter.MyViewHolder> {

        private ArrayList<Wisata> dataList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            SwipeLayout swipeLayout;
            TextView tvNamaWisata, tvNominalBayar, tvBulan, textView9, tvPeriode;
            LinearLayout tvGeser_Kekiri, tvclick;
            ImageView img_android;

            public MyViewHolder(View view) {
                super(view);
                tvNamaWisata        = (TextView) view.findViewById(R.id.tvNamaWisata);
                //tvBulan             = (TextView) view.findViewById(R.id.tvBulan);
                //tvPeriode           = (TextView) view.findViewById(R.id.tvPeriode);

                swipeLayout         =  (SwipeLayout) view.findViewById(R.id.swipe);
                tvGeser_Kekiri      =  (LinearLayout) view.findViewById(R.id.geser_kekiri);
                tvclick             =  (LinearLayout) view.findViewById(R.id.bottom_wrapper1);
                img_android         =  (ImageView) view.findViewById(R.id.tvGambar);

                swipeLayout.addDrag(SwipeLayout.DragEdge.Left, view.findViewById(R.id.geser_kekiri));
            }
        }

        public vaAdapter(ArrayList<Wisata> dataList) {
            this.dataList = dataList;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public vaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list_wisata_content, parent, false);

            // create ViewHolder
            MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            if(!dataList.isEmpty()) {

                if (dataList.size() < position) {
                    position = dataList.size() - 1;
                }

                final Wisata wisata = dataList.get(position);

                holder.tvNamaWisata.setText(wisata.getJudul());

                Picasso.with(context).load(wisata.getGambar()).resize(60, 60).into(holder.img_android);

                //holder.tvBulan.setText("-");
                //holder.tvPeriode.setText("-");

                holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

                final Cursor data_bayar = dbhelper.query("SELECT * FROM WISATA");

                holder.tvGeser_Kekiri.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

//                            Toast.makeText(getApplicationContext(), "anda geser kiri", Toast.LENGTH_LONG).show();
                            Intent preview = new Intent(ListWisataActivity.this, LokasiActivity.class);
                            preview.putExtra("judul_pengumuman", wisata.getJudul());
                            startActivity(preview);

                        }
                    });

                    holder.tvclick.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            //Toast.makeText(getApplicationContext(), wisata.getJudul(), Toast.LENGTH_LONG).show();
                            Intent preview = new Intent(ListWisataActivity.this, LokasiActivity.class);
                            preview.putExtra("judul_pengumuman", wisata.getJudul());
                            startActivity(preview);
                        }
                    });


            }
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        // Remove a RecyclerView item containing a specified Data object
        public void remove(Wisata wisata) {
            try{
                int position = dataList.indexOf(wisata);
                dataList.remove(position);
                notifyItemRemoved(position);
            }catch (ArrayIndexOutOfBoundsException e){

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
