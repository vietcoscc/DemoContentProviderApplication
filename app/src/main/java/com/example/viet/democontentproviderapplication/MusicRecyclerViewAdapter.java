package com.example.viet.democontentproviderapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by viet on 01/08/2017.
 */

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Music> mArrMusic;

    public MusicRecyclerViewAdapter(ArrayList<Music> arrMusic) {
        this.mArrMusic = arrMusic;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_recycler_view, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
        Music music = mArrMusic.get(position);
        musicViewHolder.tvTitle.setText(music.getTitle() + " ");
        musicViewHolder.tvDisplayName.setText(music.getDisplayName() + " ");
        musicViewHolder.tvAlbum.setText(music.getAlbum() + " ");
        musicViewHolder.tvArtist.setText(music.getArtist() + " ");
        musicViewHolder.tvDuration.setText(music.getDuration() + " ");
        musicViewHolder.tvMimeType.setText(music.getMimeType() + " ");
    }

    @Override
    public int getItemCount() {
        return mArrMusic.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDisplayName)
        TextView tvDisplayName;
        @BindView(R.id.tvMimeType)
        TextView tvMimeType;
        @BindView(R.id.tvAlbum)
        TextView tvAlbum;
        @BindView(R.id.tvArtist)
        TextView tvArtist;
        @BindView(R.id.tvDuration)
        TextView tvDuration;

        public MusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(view, getPosition());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
}
