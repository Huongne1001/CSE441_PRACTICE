package com.example.ex09;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {

    MediaPlayer mymedia;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mymedia =
                MediaPlayer.create(MyService.this,R.raw.trentinhbanduoitinhyeu);
        mymedia.setLooping(true); //Cho phép lặp lại liên tục
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
        if (mymedia.isPlaying())
            mymedia.pause();
        else
            mymedia.start();
        return super.onStartCommand(intent, flags, startId);
    }
    //Gọi Hàm onDestroy để dừng đối tượng mà Service quản lý
    @Override
    public void onDestroy() {
        super.onDestroy();
        mymedia.stop();
    }
}