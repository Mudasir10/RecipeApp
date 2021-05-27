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
import com.mudasir.recipeapp.fragments.Achar_Recipes;
import com.mudasir.recipeapp.fragments.Appetizer_And_Snack_Recipes;
import com.mudasir.recipeapp.fragments.Baking_Recipes;
import com.mudasir.recipeapp.fragments.Barbeque_Recipes;
import com.mudasir.recipeapp.fragments.Beef_Mutton_Recipes;
import com.mudasir.recipeapp.fragments.Bread_Recipes;
import com.mudasir.recipeapp.fragments.Breakfast_Recipes;
import com.mudasir.recipeapp.fragments.Cake_Recipes;
import com.mudasir.recipeapp.fragments.Chicken_Recipes;
import com.mudasir.recipeapp.fragments.Chinese_Recipes;
import com.mudasir.recipeapp.fragments.Chutney_Recipes;
import com.mudasir.recipeapp.fragments.Dal_Recipes;
import com.mudasir.recipeapp.fragments.Desserts_Recipes;
import com.mudasir.recipeapp.fragments.Diet_Foods;
import com.mudasir.recipeapp.fragments.Drink_Recipes;
import com.mudasir.recipeapp.fragments.Eid_Recipes;
import com.mudasir.recipeapp.fragments.Eid_Ul_Adha_Recipes;
import com.mudasir.recipeapp.fragments.Gujarati_Recipes;
import com.mudasir.recipeapp.fragments.Iftar_Items;
import com.mudasir.recipeapp.fragments.Italian_Recipes;
import com.mudasir.recipeapp.fragments.Mexican_Recipes;
import com.mudasir.recipeapp.fragments.Microwave_Recipes;
import com.mudasir.recipeapp.fragments.Mughlai_Recipes;
import com.mudasir.recipeapp.fragments.PakistaniRecipes;
import com.mudasir.recipeapp.fragments.Pie_Recipes;
import com.mudasir.recipeapp.fragments.Quick_Recipes;
import com.mudasir.recipeapp.fragments.Raita_Recipes;
import com.mudasir.recipeapp.fragments.Rice_Recipes;
import com.mudasir.recipeapp.fragments.Riwaiti_Mithaiyan;
import com.mudasir.recipeapp.fragments.Salad_Recipes;
import com.mudasir.recipeapp.fragments.Sauce_Recipes;
import com.mudasir.recipeapp.fragments.Sea_Food_Recipes;
import com.mudasir.recipeapp.fragments.Soup_Recipes;
import com.mudasir.recipeapp.fragments.Tea_Recipes;
import com.mudasir.recipeapp.fragments.Thai_Recipes;
import com.mudasir.recipeapp.fragments.Vegetable_Recipes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    String[] categories={"Pakistani Recipes","Appetizer And Snack Recipes","Chicken Recipes",
            "Desserts Recipes","Vegetable Recipes","Beef Mutton Recipes","Sea Food Recipes","Rice Recipes","Iftar Items"
            ,"Drink Recipes","Eid-Ul-Adha Recipes","Riwaiti Mithaiyan","Breakfast Recipes","Cake Recipes","Barbeque Recipes"
            ,"Chinese Recipes","Italian Recipes","Salad Recipes","Bread Recipes","Dal Recipes","Soup Recipes","Chutney Recipes"
            ,"Thai Recipes","Eid Recipes","Tea Recipes","Baking Recipes","Raita Recipes","Pie Recipes","Microwave Recipes","Diet Foods"
            ,"Sauce Recipes","Gujarati Recipes","Achar Recipes","Mughlai Recipes","Mexican Recipes","Quick Recipes"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tabs);

        SectionPagerAdapter adpater=new SectionPagerAdapter(getSupportFragmentManager());

        adpater.addFragment(new PakistaniRecipes(),"Pakistani Recipes");
        adpater.addFragment(new Appetizer_And_Snack_Recipes(),"Appetizer And Snack Recipes");
        adpater.addFragment(new Chicken_Recipes(),"Chicken Recipes");
        adpater.addFragment(new Desserts_Recipes(),"Desserts Recipes");
        adpater.addFragment(new Vegetable_Recipes(),"Vegetable Recipes");
        adpater.addFragment(new Beef_Mutton_Recipes(),"Beef Mutton Recipes");
        adpater.addFragment(new Sea_Food_Recipes(),"Sea Food Recipes");
        adpater.addFragment(new Rice_Recipes(),"Rice Recipes");
        adpater.addFragment(new Iftar_Items(),"Iftar Items");
        adpater.addFragment(new Drink_Recipes(),"Drink Recipes");
        adpater.addFragment(new Eid_Ul_Adha_Recipes(),"Eid-Ul-Adha Recipes");
        adpater.addFragment(new Riwaiti_Mithaiyan(),"Riwaiti Mithaiyan");
        adpater.addFragment(new Breakfast_Recipes(),"Breakfast Recipes");
        adpater.addFragment(new Cake_Recipes(),"Cake Recipes");
        adpater.addFragment(new Barbeque_Recipes(),"Barbeque Recipes");
        adpater.addFragment(new Chinese_Recipes(),"Chinese Recipes");
        adpater.addFragment(new Italian_Recipes(),"Italian Recipes");

        adpater.addFragment(new Salad_Recipes(),"Salad Recipes");
        adpater.addFragment(new Bread_Recipes(),"Bread Recipes");
        adpater.addFragment(new Dal_Recipes(),"Dal Recipes");
        adpater.addFragment(new Soup_Recipes(),"Soup Recipes");
        adpater.addFragment(new Chutney_Recipes(),"Chutney Recipes");
        adpater.addFragment(new Thai_Recipes(),"Thai Recipes");
        adpater.addFragment(new Eid_Recipes(),"Eid Recipes");
        adpater.addFragment(new Tea_Recipes(),"Tea Recipes");
        adpater.addFragment(new Baking_Recipes(),"Baking Recipes");
        adpater.addFragment(new Raita_Recipes(),"Raita Recipes");
        adpater.addFragment(new Pie_Recipes(),"Pie Recipes");
        adpater.addFragment(new Microwave_Recipes(),"Microwave Recipes");
        adpater.addFragment(new Diet_Foods(),"Diet Foods");
        adpater.addFragment(new Sauce_Recipes(),"Sauce Recipes");
        adpater.addFragment(new Gujarati_Recipes(),"Gujarati Recipes");
        adpater.addFragment(new Achar_Recipes(),"Achar Recipes");
        adpater.addFragment(new Mughlai_Recipes(),"Mughlai Recipes");
        adpater.addFragment(new Mexican_Recipes(),"Mexican Recipes");
        adpater.addFragment(new Quick_Recipes(),"Quick Recipes");

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