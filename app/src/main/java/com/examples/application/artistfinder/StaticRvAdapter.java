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


import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    Context context;

    public StaticRvAdapter(ArrayList<StaticRvModel> items,Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rev_item,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, final int position) {

        final StaticRvModel currenItem = items.get(position);
        holder.imageView.setImageResource(currenItem.getImage());
        holder.textView.setText(currenItem.getText());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                row_index = position;
                notifyDataSetChanged();

                Intent intent = new Intent(context,ListOfArtists.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",currenItem.getText());
                context.startActivity(intent);

            }
        });

        if(row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected);
        }
        else{
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_background);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image3);
            textView = itemView.findViewById(R.id.text3);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
