package com.examples.application.artistfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



public class ArtistCategories extends AppCompatActivity {

    // Recycler View 2
    RecyclerView recyclerView1;
    ArtistTypeAdapter adapter_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_categories);

        // Recycler view 2
        ArtistData[] artistData = new ArtistData[]{
                new ArtistData("Paintings", R.drawable.p),
                new ArtistData("Architecture", R.drawable.a),
                new ArtistData("Interior Designer", R.drawable.i),
                new ArtistData("Macrame Creatives", R.drawable.m),
                new ArtistData("Wall Hangings", R.drawable.w),
        };

        recyclerView1 = findViewById(R.id.rv_2);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        adapter_1 = new ArtistTypeAdapter(artistData, ArtistCategories.this);
        recyclerView1.setAdapter(adapter_1);
    }

}
