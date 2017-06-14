package com.example.blacksmith.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nurul on 5/23/2017.
 */

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Model> kaktus2 = new ArrayList<>();
    FetchData jsondata = new FetchData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            kaktus2 = jsondata.execute("http://takbir.pe.hu/history?id=1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void history(View v) {
        Intent intent = new Intent(MainActivity.this, History.class);
        startActivity(intent);
    }

    public void mycactus(View v) {
        Intent intent = new Intent(MainActivity.this, MyCactus.class);
        startActivity(intent);
    }

    public void mydeveloper(View v) {
        Intent intent = new Intent(MainActivity.this, AboutUs.class);
        startActivity(intent);
    }


    public void library(View v) {
        Intent intent = new Intent(MainActivity.this, Library.class);
        startActivity(intent);
    }
}