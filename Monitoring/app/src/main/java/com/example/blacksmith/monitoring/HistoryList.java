package com.example.blacksmith.monitoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nurul on 5/22/2017.
 */

public class HistoryList extends BaseAdapter {
    ArrayList<Model> kaktus;
    Context context;
    HistoryList(Context c){
        this.kaktus = MainActivity.kaktus2;
        context = c;
    }
    @Override
    public int getCount() {
        return kaktus.size();
    }

    @Override
    public Object getItem(int position) {
        return kaktus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.model,parent,false);
        ImageView icon = (ImageView) row.findViewById(R.id.iconstatus);
        TextView id = (TextView) row.findViewById(R.id.tvid);
        TextView nilai = (TextView) row.findViewById(R.id.tvnilai);
        TextView status = (TextView) row.findViewById(R.id.tvstatus);
        TextView jam = (TextView) row.findViewById(R.id.tvjam);
        Model temp = kaktus.get(position);

        if(temp.nilai_kelembapan <= 300){                                       //set gambar untuk setiap kelembaban
            icon.setImageResource(R.drawable.sad);                              //set gambar kaktus sedih untuk setiap kelembaban kering
        }else if(temp.nilai_kelembapan >300 && temp.nilai_kelembapan <= 700){
            icon.setImageResource(R.drawable.happy);                            //set gambar kaktus bahagia untuk setiap kelembaban cukup
        }else{
            icon.setImageResource(R.drawable.panic);                            //set gambar kaktus panic untuk setiap kelembaban in water
        }
        id.setText("pengambilan data ke-"+Integer.toString(temp.id));           //set text dari database
        nilai.setText("Kelembaban : "+String.valueOf(temp.nilai_kelembapan)); //set text dari database nilai_kelembaban
        status.setText("Status : "+temp.status_kelembapan);                 //set text dari database status kelembaban
        jam.setText("Waktu    \t: "+temp.jam);                                   //set text dari database jam
        return row;
    }
}
