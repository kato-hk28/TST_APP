package com.example.tst_android_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


public class DistanceHttpGetTask extends AsyncTask<Void, Void, String> {
    private TextView mTextView;
    private Activity mParentActivity;
    private ProgressDialog mDialog = null;
    private String distance;


    public DistanceHttpGetTask(Activity parentActivity, TextView textView){
        this.mParentActivity = parentActivity;
        this.mTextView = textView;
    }

    @Override
    protected void onPreExecute(){
        Log.d("Distance", "onPreExecute");
        mDialog = new ProgressDialog(mParentActivity);
        mDialog.setMessage("");
        mDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return exec_get();
    }

    @Override
    protected void onPostExecute(String string){
        mDialog.dismiss();
        distance = "measurement error";
        try{
            JSONObject rootJSON = new JSONObject(string);
            distance = rootJSON.getString("distance");
        }catch (JSONException e){
            e.printStackTrace();
        }
        this.mTextView.setText(distance);
        Log.d("Distance", distance);
    }

    private String exec_get() {
        HttpURLConnection urlConnection;
        InputStream inputStream;
        String result = "";
        String str = "";

        Log.d("WebSample", "get API");

        try{
            URL url = new URL("http://192.168.1.6/~pi/distance_sensor.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.addRequestProperty("User-Agent", "Android");
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setDoInput(true);

            Log.d("Distance", "set up is done.");

            urlConnection.connect();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode == 200){
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                result = bufferedReader.readLine();
                while(result != null){
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }
}
