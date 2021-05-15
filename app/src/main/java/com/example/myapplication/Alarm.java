package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.start();
        Toast.makeText(context, "Hey, it's time to record your pain dairy.", Toast.LENGTH_SHORT).show();
    }
}
