package com.example.tst_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ManualModeButton = (Button) findViewById(R.id.manual_button);
        ManualModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("main", "plog manual_button onClick");

                Intent intent = new Intent(getApplicationContext(), Manual_Activity.class);
                startActivity(intent);
            }
        });
    }
}