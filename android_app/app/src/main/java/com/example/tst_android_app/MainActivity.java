package com.example.tst_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String ip_address="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        IPアドレスをうけとる場合
//        EditText et = (EditText) findViewById(R.id.ip_edit);

        Button ManualModeButton = (Button) findViewById(R.id.manual_button);
        ManualModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("main", "plog manual_button onClick");

//                IPアドレスをうけとる場合
//                ip_address = et.getText().toString();

                Intent intent = new Intent(getApplicationContext(), Manual_Activity.class);
                startActivity(intent);
//                if(et.getText().toString().isEmpty()) {
//                    et.setError("Input IP address!");
//                }else{
//                    // Stringの値を渡す
//                    intent.putExtra("ip_address", ip_address);
//
//                    startActivity(intent);
//                }
            }
        });
    }
}