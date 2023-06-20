package com.example.tst_android_app;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Manual_Activity extends AppCompatActivity implements View.OnTouchListener{

    private Button mButton_forwrd, mButton_backward, mButton_left, mButton_right, mButton_throw;
    private TextView mControlText;
    public static int FORWARD = 0;
    public static int BACKWARD = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public static int STOP = 4;
    private int state = STOP;
    private MotorHttpGetTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_activity);

        //windowをフルスクリーンモードにする
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // ボタンの定義してonClick, onLongClickListenerをつける
        mButton_forwrd = (Button) findViewById(R.id.forward_button);
        mButton_backward = (Button) findViewById(R.id.backward_button);
        mButton_left = (Button) findViewById(R.id.left_button);
        mButton_right = (Button) findViewById(R.id.right_button);
        mButton_throw = (Button) findViewById(R.id.throw_button);

        mButton_forwrd.setOnTouchListener(this);
        mButton_backward.setOnTouchListener(this);
        mButton_left.setOnTouchListener(this);
        mButton_right.setOnTouchListener(this);
        mButton_throw.setOnTouchListener(this);

        mControlText = (TextView) findViewById(R.id.control_text);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("onTouch", "plog onTouchEvent is called.");
        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
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
                    } else {

                    }
                }
                TaskCreate(state);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("onTouch", "plog STATE=STOP");
                state = STOP;
                TaskCreate(state);
                break;
        }
        return false;
    }

    private void TaskCreate(int state){
        task = new MotorHttpGetTask(this);
        // Listenerを設定
        //task.setListener(createListener());
        task.execute(state);
        mControlText.setText("STATE = "+state);
    }
}
