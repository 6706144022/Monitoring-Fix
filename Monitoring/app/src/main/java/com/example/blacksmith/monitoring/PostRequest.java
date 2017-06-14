package com.example.blacksmith.monitoring;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Nurul on 6/1/2017.
 */

public class PostRequest extends AsyncTask<Void, JSONObject, JSONObject> {
    private final String TAG = "Post Request";
    private String urlStr;
    private StringBuilder params;
    private PostRequestCallback postRequestCallback;

    public PostRequest(String urlStr) {
        this.urlStr = urlStr;
        params = new StringBuilder();
    }

    public void addParam(String name, String value){
        if(params.length() > 0){
            params.append("&");
        }

        params.append(name)
                .append("=")
                .append(value);
    }

    public void setPostRequestCallback(PostRequestCallback postRequestCallback) {
        this.postRequestCallback = postRequestCallback;
    }

    @Override
    protected JSONObject doInBackground(Void... Void) {
        try {
            URL url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            String urlParameters = params.toString();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();
            int responseCode = connection.getResponseCode();

            Log.d(TAG, "Sending 'POST' request to URL : " + url);
            Log.d(TAG, "Post parameters : " + urlParameters);
            Log.d(TAG, "Response Code : " + responseCode);

            final StringBuilder output = new StringBuilder("Request URL " + url);
            output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
            output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
            output.append(System.getProperty("line.separator")  + "Type " + "POST");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
//            System.out.println("output===============" + br);
            while((line = br.readLine()) != null ) {
                responseOutput.append(line);
            }
            br.close();
            Log.d(TAG, "Response body : " + responseOutput.toString());

            output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());
            return new JSONObject(responseOutput.toString());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if(postRequestCallback != null)
            postRequestCallback.onExcecuted(jsonObject);
    }

    public interface PostRequestCallback{
        public void onExcecuted(JSONObject json);
    }
}
