package com.example.blacksmith.monitoring;

import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Nurul on 5/21/2017.
 */

class FetchData extends AsyncTask<String, Double, ArrayList<Model>> {
    ArrayList<Model> kaktus= new ArrayList<>();


    @Override
    protected ArrayList<Model> doInBackground(String... params) {
        HttpURLConnection connection = null;

        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJSON = buffer.toString();
            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray parentArray = parentObject.getJSONArray("data");
            if(parentArray.length() > 7) {
                for (int i = parentArray.length()-1; i > parentArray.length()-8; i--) { //mengambil data dari database server dengan urutan pengambilan 8 data terakhir
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    int id = finalObject.getInt("id");
                    Double nilai_kelembapan = finalObject.getDouble("nilai_kelembapan");
                    String status_kelembapan = finalObject.getString("status_kelembapan");
                    String jam = finalObject.getString("updated_at");
                    //String[] waktu = jam.split(" ");
                    Model model = new Model(id, nilai_kelembapan, status_kelembapan, jam); //waktu[1]);
                    kaktus.add(model);
                }
            }else{
                for (int i = 0; i < parentArray.length(); i++) {        //mengambil data apabila data di db.server < jumlah data
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    int id = finalObject.getInt("id");
                    Double nilai_kelembapan = finalObject.getDouble("nilai_kelembapan");
                    String status_kelembapan = finalObject.getString("status_kelembapan");
                    String jam = finalObject.getString("updated_at");
                    //String[] waktu = jam.split(" ");
                    Model model = new Model(id, nilai_kelembapan, status_kelembapan, jam); //waktu[1]);
                    kaktus.add(model);
                }
            }

            return kaktus;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
