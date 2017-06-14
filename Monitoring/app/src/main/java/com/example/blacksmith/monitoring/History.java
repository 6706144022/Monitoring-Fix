package com.example.blacksmith.monitoring;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Nurul on 18/04/17.
 */

public class History extends AppCompatActivity{

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new HistoryList(this)); //memanggil class HistoryList

    }


}
