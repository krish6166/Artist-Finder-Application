package com.examples.application.artistfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // Declarations
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView menuIcon;
    MenuItem menuItem;
    NavigationView navigationView;

    //RecyclerView Static rec view 1
    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Static Recycler View
        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.pune, "Pune"));
        item.add(new StaticRvModel(R.drawable.mumbai, "Mumbai"));
        item.add(new StaticRvModel(R.drawable.delhi, "Delhi"));
        item.add(new StaticRvModel(R.drawable.jaipur, "Jaipur"));
        item.add(new StaticRvModel(R.drawable.chennai, "Chennai"));
        item.add(new StaticRvModel(R.drawable.hyderabad, "Hyderabad"));
        item.add(new StaticRvModel(R.drawable.bengaluru, "Bengaluru"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        menuIcon = findViewById(R.id.menu_icon);
        navigationView = findViewById(R.id.nav_view);

        // Calling Functions
        navigattionDrawer();

        // calling profile
        ImageView profile = findViewById(R.id.profile_icon);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        // Image Slider
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel(R.drawable.krish));
        list.add(new SlideModel(R.drawable.krish2));
        list.add(new SlideModel(R.drawable.krish3));
        list.add(new SlideModel(R.drawable.krish4));
        imageSlider.setImageList(list, true);

    }

    // Navigation Drawer Functions
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    private void navigattionDrawer() {

        navigationView.bringToFront();

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case  R.id.nav_home:

                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case  R.id.nav_categories:

                        Intent intent1 = new Intent(MainActivity.this, ArtistCategories.class);
                        startActivity(intent1);
                        break;

                    case  R.id.nav_profile:

                        Intent intent2 = new Intent(MainActivity.this, Profile.class);
                        startActivity(intent2);
                        break;

                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}






