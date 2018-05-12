package com.example.amansingh.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play , stop;
    String status = "play";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.playbtn);
        stop = (Button)findViewById(R.id.stopbtn);

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (status == "play")
                {
                    if(count == 0)
                    {
                        Intent i = new Intent(MainActivity.this, Service.class);
                        i.putExtra("status",status);
                        play.setText("Pause");
                        count = 1;
                        startService(i);
                    }
                        else
                        if(count == 1)
                    {
                        Intent i = new Intent(MainActivity.this, Service.class);
                        i.putExtra("status","resume");
                        play.setText("Pause");
                        startService(i);
                    }
                    status = "pause";
                }
                else
                    if(status=="pause")
                {
                    Intent i = new Intent(MainActivity.this, Service.class);
                    i.putExtra("status",status);
                    if(count == 1) {
                        play.setText("Resume");
                    }
                    else
                    {
                        play.setText("Play");
                    }
                    startService(i);
                    status = "play";
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Service.class);
                stopService(i);
                play.setText("Play");
            }
        });
    }
}
