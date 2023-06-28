package com.example.tst_android_app;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.longdo.mjpegviewer.MjpegView;

import org.json.JSONException;
import org.json.JSONObject;
//　By https://github.com/perthcpe23/android-mjpeg-view

public class Manual_Activity extends AppCompatActivity implements View.OnTouchListener{

    private ImageButton mButton_forwrd, mButton_backward, mButton_left, mButton_right, mButton_stop, mButton_throw;
    private MjpegView mv;
    public static int FORWARD = 0;
    public static int BACKWARD = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public static int STOP = 4;
    public static int THROW = 5;
    private int state = STOP;
    private MotorHttpGetTask task;
    private String ip = "192.168.11.24";
    private String DEFAULTURL = "http://" + ip + ":8080/?action=stream";
    private TextView mDistanceText;
    private DistanceHttpGetTask distanceTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_activity);

        //windowをフルスクリーンモードにする
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //ラズパイ上のカメラからStream配信を取得
        mv = (MjpegView) findViewById(R.id.mjpegview);
        StartStream(mv);

        // ボタンの定義してonClick, onLongClickListenerをつける
        mButton_forwrd = (ImageButton) findViewById(R.id.forward_button);
        mButton_backward = (ImageButton) findViewById(R.id.backward_button);
        mButton_left = (ImageButton) findViewById(R.id.left_button);
        mButton_right = (ImageButton) findViewById(R.id.right_button);
        mButton_stop = (ImageButton) findViewById(R.id.stop_button);
        mButton_throw = (ImageButton) findViewById(R.id.throw_button);

        mButton_forwrd.setOnTouchListener(this);
        mButton_backward.setOnTouchListener(this);
        mButton_left.setOnTouchListener(this);
        mButton_right.setOnTouchListener(this);
        mButton_stop.setOnTouchListener(this);
        mButton_throw.setOnTouchListener(this);

        mDistanceText = (TextView) findViewById(R.id.distance_text);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        Log.d("onTouch", "plog onTouchEvent is called.");
        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                if(distanceTask != null){
                    distanceTask.cancel(true);
                }
                if (event.getPointerCount() == 1) {
                    if (v.getId() == R.id.forward_button) {
                        Log.d("onTouch", "plog STATE=FORWARD");
                        state = FORWARD;
                    } else if (v.getId() == R.id.backward_button) {
                        Log.d("onTouch", "plog STATE=BACKWARD");
                        state = BACKWARD;
                    } else if (v.getId() == R.id.left_button) {
                        Log.d("onTouch", "plog STATE=LEFT");
                        state = LEFT;
                    } else if (v.getId() == R.id.right_button) {
                        Log.d("onTouch", "plog STATE=RIGHT");
                        state = RIGHT;
                    }  else if (v.getId() == R.id.stop_button) {
                        Log.d("onTouch", "plog STATE=STOP");
                        state = STOP;
                    } else if (v.getId() == R.id.throw_button) {
                        Log.d("onTouch", "plog STATE=THROW");
                        state = THROW;
                    } else {

                    }
                }
                TaskCreate(state);
                distanceTask = GetDistance();
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "plog STATE=STOP");
                distanceTask.cancel(true);
                state = STOP;
                TaskCreate(state);
                break;
        }
        return false;
    }

    private void TaskCreate(int state){
        task = new MotorHttpGetTask(this, ip);
        task.execute(state);
//        mControlText.setText("STATE = "+state);
    }

    private DistanceHttpGetTask GetDistance(){
        Log.d("Distance", "GetDistance()");
        DistanceHttpGetTask distanceTask = new DistanceHttpGetTask(this, mDistanceText, ip);
        distanceTask.execute();
        return distanceTask;
    }

    private void StartStream(MjpegView viewer){
        viewer.setMode(MjpegView.MODE_FIT_WIDTH);
        viewer.setAdjustHeight(true);
        viewer.setSupportPinchZoomAndPan(true);
        viewer.setUrl(DEFAULTURL);
        viewer.startStream();
    }
}
