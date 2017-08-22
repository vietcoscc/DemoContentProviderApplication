package com.example.viet.democontentproviderapplication;

import android.app.Application;

/**
 * Created by viet on 01/08/2017.
 */

public class ApplicationData extends Application {
    private boolean mIsPlaying = false;

    public boolean ismIsPlaying() {
        return mIsPlaying;
    }

    public void setmIsPlaying(boolean mIsPlaying) {
        this.mIsPlaying = mIsPlaying;
    }
}
