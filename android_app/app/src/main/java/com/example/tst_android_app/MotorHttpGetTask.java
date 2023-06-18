package com.example.tst_android_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MotorHttpGetTask extends AsyncTask<Integer, Void, Void> {

    private final Activity mParentActivity;
    private final String DEFAULTUAL;
    private String uri = null;
    private ProgressDialog mDialog = null;

    public MotorHttpGetTask(Activity mParentActivity, String defaultual) {
        this.mParentActivity = mParentActivity;
        DEFAULTUAL = defaultual;
    }
    //タスク開始時
    @Override
    protected void onPreExecute(){
        Log.d("MotorHttpGetTask", "wlog onPreExecute()");
        mDialog = new ProgressDialog(mParentActivity);
        mDialog.setMessage("通信中・・・");
        mDialog.show();
    }


    //メイン処理
    @Override
    protected Void doInBackground(Integer... arg0){
        Log.d("MotorHttpGetTask", "wlog doInBackground()");
        //実行するURLスクリプト
        uri = DEFAULTUAL + "num=" + arg0[0].toString() + "&stat=" + arg0[1].toString();
        exec_get();
        return null;
    }

    //タスク終了時
    @Override
    protected void onPostExecute(Void result){
        Log.d("MotorHttpGetTask", "wlog onPostExecute()");
        mDialog.dismiss();
    }

    private String exec_get(){
        Log.d("MotorHttpGetTask", "wlog exec_get()");
        HttpURLConnection http = null;
        InputStream in = null;
        String src = new String();

        try{
            Log.d("MotorHttpGetTask", "in try");
            // URLにHTTP接続
            URL url = new URL(uri);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            // データを取得
            in = http.getInputStream();
            // ソースを読み出す
            byte[] line = new byte[1024];
            int size;
            while(true){
                Log.d("MotorHttpGetTask", "while read");
                size = in.read(line);
                if (size <= 0){
                    break;
                }
                src += new String(line);
            }
            Log.d("MotorHttpGetTask", "out while");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Log.d("MotorHttpGetTask", "disconnect");
            try{
                if (http != null){
                    http.disconnect();
                }
                if(in != null){
                    in.close();
                }
            }catch (Exception ignored){
            }
        }
        return src;
    }
}
