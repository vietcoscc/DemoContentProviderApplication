package com.example.viet.democontentproviderapplication;

import android.app.Application;

/**
 * Created by viet on 01/08/2017.
 */

public class ApplicationData extends Application {
    private boolean isPlaying = false;

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
