package com.example.blacksmith.monitoring;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MyCactus extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    public static ArrayList<Model> kaktus2 = new ArrayList<>();


    ImageView gambar;
    TextView tvStatus,tvKelembaban,tvAction;

    ProgressDialog progress ;

    Model data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Model data = MainActivity.kaktus.get(MainActivity.kaktus.size()-1);
        //Model data = MainActivity.kaktus2.get(MainActivity.kaktus2.size()-1); //inisialisasi Model.class kedalam variabel data


        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycactus);
        progress = new ProgressDialog(this);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //ngapain
                Toast.makeText(MyCactus.this, "refresh", Toast.LENGTH_SHORT).show();
                fetchData();
            }
        });

        progress.setMessage("Mengambil data");

        gambar = (ImageView) findViewById(R.id.img_status);

        tvStatus = (TextView) findViewById(R.id.tv_status);
        tvKelembaban = (TextView) findViewById(R.id.tv_humidity);
        tvAction = (TextView) findViewById(R.id.tv_action);

        fetchData();

    }

    private void fetchData(){
        FetchData jsondata = new FetchData();
        if(jsondata.getStatus() == AsyncTask.Status.RUNNING){
            // My AsyncTask is currently doing work in doInBackground()
            Toast.makeText(this, "already running", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.show();
        try {
            kaktus2 = jsondata.execute("http://takbir.pe.hu/ambilkaktus").get();
            data = kaktus2.get(kaktus2.size()-1); //inisialisasi Model.class kedalam variabel data
            Toast.makeText(this, data.status_kelembapan, Toast.LENGTH_SHORT).show();
            if (data.nilai_kelembapan<=300){
                gambar.setImageResource(R.drawable.sad);            //set gambar di my cactus sesuai nilai kelembaban
            }else if(data.nilai_kelembapan>300&&data.nilai_kelembapan<=700){
                gambar.setImageResource(R.drawable.happy);
            }else{
                gambar.setImageResource(R.drawable.panic);
            }

            tvStatus.setText(data.status_kelembapan);
            tvKelembaban.setText(data.nilai_kelembapan.toString());

            if (data.nilai_kelembapan<=300){
                tvAction.setText("siram dengan air dan simpan dengan pencahayaan tidak langsung");
            }else if(data.nilai_kelembapan>300&&data.nilai_kelembapan<=700){
                tvAction.setText("air sudah cukup, usahakan pencahayaan baik");
            }else{
                tvAction.setText("terlalu banyak air!! simpan ditempat dengan pencahayaan baik");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){

        }

        refreshLayout.setRefreshing(false);
        progress.dismiss();
    }
}