package com.mudasir.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabLayout;
import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.adapters.SectionPagerAdapter;
import com.mudasir.recipeapp.fragments.PakistaniRecipes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tabs);

        SectionPagerAdapter adpater=new SectionPagerAdapter(getSupportFragmentManager());
        adpater.addFragment(new PakistaniRecipes(),"Pakistani Recipes");
        viewPager.setAdapter(adpater);
        tabLayout.setupWithViewPager(viewPager);

        List<SlideModel> imageList = new ArrayList<SlideModel>();

        imageList.add(new SlideModel(R.drawable.image_4, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image_2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image_4, ScaleTypes.FIT));

        ImageSlider imageSlider = findViewById(R.id.image);

                imageSlider.setImageList(imageList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       int id= item.getItemId();
        switch (id){
            case R.id.fav:
                Toast.makeText(this, "Favorite Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_main:
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account:
                startActivity(new Intent(this,AcountDetails.class));
                break;
        }
        return true;
    }
}