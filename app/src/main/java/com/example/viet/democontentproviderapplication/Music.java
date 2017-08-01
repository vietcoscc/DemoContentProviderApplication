package com.example.viet.democontentproviderapplication;

import android.provider.MediaStore;

/**
 * Created by viet on 01/08/2017.
 */

public class Music {

    String title;
    String displayName;
    String mimeType;
    String album;
    String artist;
    int duration;
    String data;

    public Music(String title, String displayName, String mimeType, String album, String artist, int duration, String data) {
        this.title = title;
        this.displayName = displayName;
        this.mimeType = mimeType;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getData() {
        return data;
    }
}
