package com.example.blacksmith.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

/**
 * Created by Nurul on 5/23/2017.
 */

public class Library extends AppCompatActivity {

    Button Tips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

    }
    public void tips(View v) {
        Intent intent = new Intent(Library.this, Tips.class);
        startActivity(intent);
    }
}

