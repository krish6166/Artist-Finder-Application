package com.examples.application.artistfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListOfArtists extends AppCompatActivity {


    RecyclerView recview;
    DynamicRecyclerViewAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_artists);


        Intent intent = getIntent();
        String type = intent.getStringExtra("name");


        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Location").child(type), model.class)
                        .build();

        adapter1 = new DynamicRecyclerViewAdapter(options);
        recview.setAdapter(adapter1);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter1.stopListening();
    }
}
