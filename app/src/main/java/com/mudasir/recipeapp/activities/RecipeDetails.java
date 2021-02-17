package com.mudasir.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mudasir.recipeapp.R;

public class RecipeDetails extends AppCompatActivity {

    String name, ing, make, key, url;

    RatingBar ratingBar;
    TextView ingredents, making;
    ImageView imageView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ingredents = findViewById(R.id.ingredents);
        making = findViewById(R.id.howto);
        imageView=findViewById(R.id.recipe_image);
        progressBar=findViewById(R.id.progress);


        ratingBar = findViewById(R.id.DetailsRatingbar);
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(RecipeDetails.this);
            LayoutInflater inflater = getLayoutInflater();
            builder.setTitle("RATE " + name);
            View dialogLayout = inflater.inflate(R.layout.rating_dialog, null);
            final RatingBar ratingBar1 = dialogLayout.findViewById(R.id.ratingBarDialog);
            ratingBar1.setRating(rating);
            builder.setView(dialogLayout);
            builder.setPositiveButton("Submit", (dialog, which) -> {
                Toast.makeText(this, "Submited", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("Cancel", (dialog, which) -> {

                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            });
            builder.show();

        });

        if (getIntent()!=null){
            name = getIntent().getExtras().get("name").toString();
            ing = getIntent().getExtras().get("ing").toString();
            make = getIntent().getExtras().get("make").toString();
            key = getIntent().getExtras().get("key").toString();
            url = getIntent().getExtras().get("url").toString();
            ingredents.setText(ing);
            making.setText(make);
            getSupportActionBar().setTitle(name);

            Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).placeholder(R.drawable.bg_food).into(imageView);
        }



    }


    public void givelike(View view) {
        Toast.makeText(this, "Like Clicked", Toast.LENGTH_SHORT).show();
    }

    public void giveComment(View view) {
        Toast.makeText(this, "Comment Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnClose:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}