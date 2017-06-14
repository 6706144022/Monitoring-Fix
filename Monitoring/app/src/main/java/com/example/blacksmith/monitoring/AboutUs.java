package com.example.blacksmith.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.blacksmith.monitoring.R.layout.about;

/**
 * Created by Nurul on 5/23/2017.
 */

public class AboutUs extends AppCompatActivity {

    Button btndev, btnpembim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(about);
    }
}



