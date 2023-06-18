package com.example.tst_android_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class Manual_Activity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{

    private Button mButton_forwrd, mButton_backward, mButton_left, mButton_right, mButton_throw;
    public static int FORWARD = 00;
    public static int BACKWARD = 01;
    public static int LEFT = 10;
    public static int RIGHT = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_activity);

        // ボタンの定義してonClick, onLongClickListenerをつける
        mButton_forwrd = (Button) findViewById(R.id.forward_button);
        mButton_backward = (Button) findViewById(R.id.backward_button);
        mButton_left = (Button) findViewById(R.id.left_button);
        mButton_right = (Button) findViewById(R.id.right_button);
        mButton_throw = (Button) findViewById(R.id.throw_button);
        //
        mButton_forwrd.setOnClickListener(this);
        mButton_backward.setOnClickListener(this);
        mButton_left.setOnClickListener(this);
        mButton_right.setOnClickListener(this);
        mButton_throw.setOnClickListener(this);

        mButton_forwrd.setOnLongClickListener(this);
        mButton_backward.setOnLongClickListener(this);
        mButton_left.setOnLongClickListener(this);
        mButton_right.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        Log.d("onLongClick", String.format("ID = %d",v.getId()));
        if (v.getId() == R.id.forward_button){

        } else if (v.getId() == R.id.backward_button) {

        } else if (v.getId() == R.id.left_button) {

        } else if (v.getId() == R.id.right_button) {

        }else{

        }
        return true;
    }
}
