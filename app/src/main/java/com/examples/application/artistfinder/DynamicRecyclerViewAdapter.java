package com.examples.application.artistfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DynamicRecyclerViewAdapter extends FirebaseRecyclerAdapter<model, DynamicRecyclerViewAdapter.DynamicViewHolder> {

    Context context;
    int row_index = -1;

    public DynamicRecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DynamicViewHolder holder, int position, @NonNull final model model) {
        holder.nametext.setText(model.getName());
        holder.emailtext.setText(model.getEmail());
        holder.arttext.setText(model.getArt());
        Glide.with(holder.img1.getContext()).load(model.getA1url()).into(holder.img1);

    }

    @NonNull
    @Override
    public DynamicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new DynamicViewHolder(view);
    }

    public class DynamicViewHolder extends RecyclerView.ViewHolder {

        ImageView img1;
        TextView nametext,arttext,emailtext;

        public DynamicViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img_1);
            nametext = itemView.findViewById(R.id.name_text);
            arttext = itemView.findViewById(R.id.art_text);
            emailtext = itemView.findViewById(R.id.email_text);
        }
    }
}
