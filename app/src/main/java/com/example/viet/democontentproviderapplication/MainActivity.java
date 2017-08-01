package com.example.viet.democontentproviderapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.viet.democontentproviderapplication.MusicService.ACTION_PLAY;
import static com.example.viet.democontentproviderapplication.MusicService.ACTION_STOP;

public class MainActivity extends AppCompatActivity {
    public static final String DATA = "data";
    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        final ArrayList<Music> arrMusicMain = new ArrayList<>();
        final MusicRecyclerViewAdapter adapter = new MusicRecyclerViewAdapter(arrMusicMain);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MusicRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ApplicationData data = (ApplicationData) getApplication();
                boolean isPlaying = data.isPlaying();
                if (!isPlaying) {
                    data.setPlaying(true);
                    Log.i(TAG,"Play");
                    Toast.makeText(MainActivity.this, "PLAY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    intent.putExtra(DATA, arrMusicMain.get(position).getData());
                    intent.setAction(ACTION_PLAY);
                    startService(intent);
                } else {
                    data.setPlaying(false);
                    Log.i(TAG,"Stop");
                    Toast.makeText(MainActivity.this, "STOP", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    intent.putExtra(DATA, arrMusicMain.get(position).getData());
                    intent.setAction(ACTION_STOP);
                    startService(intent);
                }
            }
        });
        MyContentProvider myContentProvider = new MyContentProvider(this);
        myContentProvider.setOnCompleteLoadingListener(new MyContentProvider.OnCompleteLoadingListener() {
            @Override
            public void onComplete(ArrayList<Music> arrMusic) {
                arrMusicMain.clear();
                arrMusicMain.addAll(arrMusic);
                adapter.notifyDataSetChanged();
            }
        });
        myContentProvider.initLoader();
    }
}
