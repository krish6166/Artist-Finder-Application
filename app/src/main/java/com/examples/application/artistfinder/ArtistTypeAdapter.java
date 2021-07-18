package com.examples.application.artistfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ArtistTypeAdapter extends RecyclerView.Adapter<ArtistTypeAdapter.ViewHolder> {

    ArtistData[] artistData;
    Context context;

    public ArtistTypeAdapter(ArtistData[] artistData1, ArtistCategories activity){

        this.artistData = artistData1;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.artist_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ArtistData artistDatalist = artistData[position];
        holder.artistName.setText(artistDatalist.getArtistName());
        holder.artImage.setImageResource(artistDatalist.getArtImage());

    }

    @Override
    public int getItemCount() {
        return artistData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView artImage;
        TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artImage = itemView.findViewById(R.id.image_art);
            artistName = itemView.findViewById(R.id.text_artist);
        }
    }


}
