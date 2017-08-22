package com.example.viet.democontentproviderapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import static com.example.viet.democontentproviderapplication.MainActivity.DATA;

/**
 * Created by viet on 01/08/2017.
 */

public class MusicService extends Service {
    public static final String ACTION_PLAY = "action play";
    public static final String ACTION_PAUSE = "action pause";
    public static final String ACTION_NEXT = "action next";
    public static final String ACTION_PREVIOUS = "action previous";
    public static final String ACTION_STOP = "action stop";
    private static final String TAG = "MusicService";
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getAction();
        switch (action) {
            case ACTION_PLAY:
                if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();
                    ApplicationData data = (ApplicationData) getApplication();
                    data.setmIsPlaying(false);
                    return START_STICKY;
                }
                buildNotificaiton();
                mPlayer = MediaPlayer.create(this, Uri.parse(intent.getStringExtra(DATA)));
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ApplicationData data = (ApplicationData) getApplication();
                        data.setmIsPlaying(false);
                    }
                });
                mPlayer.start();
                break;
            case ACTION_PAUSE:
                //TODO:
                break;
            case ACTION_NEXT:
                //TODO:
                break;
            case ACTION_PREVIOUS:
                //TODO:
                break;
            case ACTION_STOP:
                mPlayer.stop();
                stopForeground(true);
                stopSelf();
                break;
        }
        return START_STICKY;
    }

    private void buildNotificaiton() {
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Truiton Music Player")
                .setTicker("Truiton Music Player")
                .setContentText("My Music")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous,
                        "Previous", getPendingIntent(ACTION_PREVIOUS))
                .addAction(android.R.drawable.ic_media_play, "Play",
                        getPendingIntent(ACTION_PLAY))
                .addAction(android.R.drawable.ic_media_next, "Next",
                        getPendingIntent(ACTION_NEXT)).build();
        startForeground(11, notification);
    }

    private PendingIntent getPendingIntent(String action) {
        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(action);
        return PendingIntent.getService(this, 0, intent, 0);
    }
}
