package com.example.amansingh.service;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Aman Singh on 5/10/2018.
 */

public class Service extends android.app.Service
{
    int x;
    MediaPlayer mp;
    String status = "play";
    int n = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mp = MediaPlayer.create(getApplicationContext(),R.raw.test);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if(n != 0) {
            if (intent.hasExtra("status")) {
                status = (String) intent.getExtras().get("status");
            }
        }
        Log.i("h",""+status);
        if(status.equals("pause"))
        {
            Log.i("h","paused");
            x = mp.getCurrentPosition();
            mp.pause();
            n = 1;
        }
            if(status.equals("play"))
        {
            Log.i("h","running");
            mp.start();
            n = 1;
        }
        if(status.equals("resume"))
        {
                Log.i("h","resumed");
                mp.seekTo(x);
                mp.start();
                n = 1;
        }
        mp.setLooping(true);
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        status = "play";
            mp.stop();
            mp.reset();
        super.onDestroy();
    }
}
