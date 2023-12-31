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
    private String DEFAULTUAL = "http://192.168.11.24/~pi/motorDriver.php?";
    private String uri = null;
    private ProgressDialog mDialog = null;
    public MotorHttpGetTask(Activity mParentActivity, String ip) {
        this.mParentActivity = mParentActivity;
        this.DEFAULTUAL = "http://" + ip + "/~pi/motorDriver.php?";
    }
    //タスク開始時
    @Override
    protected void onPreExecute(){
        Log.d("MotorHttpGetTask", "wlog onPreExecute()");
//        mDialog = new ProgressDialog(mParentActivity);
//        mDialog.setMessage("通信中・・・");
//        mDialog.show();
    }

    //メイン処理
    @Override
    protected Void doInBackground(Integer... arg0){
        //実行するURLスクリプト
        uri = DEFAULTUAL + "state=" + arg0[0].toString();
        exec_get();
        return null;
    }

    //タスク終了時
    @Override
    protected void onPostExecute(Void result){
//        mDialog.dismiss();
    }

    private String exec_get(){
        HttpURLConnection http = null;
        InputStream in = null;
        String src = new String();

        try{
            // URLにHTTP接続
            URL url = new URL(uri);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            // データを取得
            in = http.getInputStream();
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
