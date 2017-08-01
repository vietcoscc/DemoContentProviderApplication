package com.example.viet.democontentproviderapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by viet on 01/08/2017.
 */

public class MyContentProvider implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MyContentProvider";
    private Context context;
    private CursorLoader cursorLoader;

    public MyContentProvider(Context context) {
        this.context = context;
    }

    public void initLoader() {
        AppCompatActivity compatActivity = (AppCompatActivity) context;
        compatActivity.getSupportLoaderManager().initLoader(1, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new CursorLoader(context, MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Toast.makeText(context, data.getCount() + "", Toast.LENGTH_SHORT).show();
        ArrayList<Music> arrMusic = new ArrayList<>();
        data.moveToFirst();
        while (!data.isAfterLast()) {
            String title = data.getString(data.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String displayName = data.getString(data.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            String mimeType = data.getString(data.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE));
            String album = data.getString(data.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String artist = data.getString(data.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            int duration = data.getInt(data.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String _data = data.getString(data.getColumnIndex(MediaStore.Audio.Media.DATA));
//            Log.i(TAG, title);
//            Log.i(TAG, displayName);
//            Log.i(TAG, mimeType);
//            Log.i(TAG, album);
//            Log.i(TAG, artist);
//            Log.i(TAG, duration + "");
//            Log.i(TAG, _data);
            arrMusic.add(new Music(title, displayName, mimeType, album, artist, duration, _data));
            data.moveToNext();
        }
        if (onCompleteLoadingListener != null) {
            onCompleteLoadingListener.onComplete(arrMusic);
        }
        Log.i(TAG, "Finish");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    OnCompleteLoadingListener onCompleteLoadingListener;

    public void setOnCompleteLoadingListener(OnCompleteLoadingListener onCompleteLoadingListener) {
        this.onCompleteLoadingListener = onCompleteLoadingListener;
    }

    public interface OnCompleteLoadingListener {
        void onComplete(ArrayList<Music> arrMusic);
    }
}
