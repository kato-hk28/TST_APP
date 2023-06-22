package com.example.tst_android_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CameraHttpGetTask  extends AsyncTask<Void, Void, String>{
    private final Activity mParentActivity;
    private FrameLayout mFrame;
    private ProgressDialog mDialog = null;

    private String mUri = "http//192.168.1.89/:8080/?action=stream";
    //実行するphpのURL
    public CameraHttpGetTask(Activity parentActivity, FrameLayout frame){
        Log.d("HttpGetTask", "wlog HttpGetTask()");
        this.mParentActivity = parentActivity;
        this.mFrame = frame;
    }

    //タスク開始時
    @Override
    protected void onPreExecute(){
        Log.d("CameraHttpGetTask", "wlog onPreExecute()");
        mDialog = new ProgressDialog(mParentActivity);
        mDialog.setMessage("");
        mDialog.show();
    }
    //メイン処理
    @Override
    protected String doInBackground(Void... voids){
        Log.d("CameraHttpGetTask", "wlog doInBackground()");
        return exec_get();
    }
    //タスク終了時
    @Override
    protected void onPostExecute(String string){
        Log.d("CameraHttpGetTask", "wlog onPostExecute()");
        mDialog.dismiss();
    }

    private String exec_get(){
        Log.d("CameraHttpGetTask", "wlog exec_get()");
        HttpURLConnection http = null;
        InputStream in = null;
        String src = "";

        try{
            Log.d("CameraHttpGetTask", "in try");
            URL url = new URL(mUri);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            in = http.getInputStream();
            byte[] line = new byte[1024];
            int size;
            while(true){
                Log.d("CameraHttpGetTask", "while read");
                size = in.read(line);
                if (size <= 0){
                    break;
                }
                src += new String(line);
            }
            Log.d("CameraHttpGetTask", "out while");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Log.d("CameraHttpGetTask", "disconnect");
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
