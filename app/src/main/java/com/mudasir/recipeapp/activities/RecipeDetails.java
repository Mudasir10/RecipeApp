package com.mudasir.recipeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mudasir.recipeapp.R;

public class RecipeDetails extends AppCompatActivity {

    String name, ing, make, key, url;

    RatingBar ratingBar;
    TextView ingredents, making;
    ImageView imageView;
    ProgressBar progressBar;

    FirebaseUser mCurrentUser;
    FirebaseAuth mAuth;
    String UserId;
    boolean showingFirst = true;
    Button btngivelike;
    TextView tvlikesCount, tvcommentsCount, tvRatingsCount, tvViewCount;

    private DatabaseReference mDatabaseRefLikes;
    private DatabaseReference mDatabaseRefRatings;
    private DatabaseReference mDatabaseRefViews;
    private DatabaseReference mDatabaseRefComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        UserId = mCurrentUser.getUid();



        ingredents = findViewById(R.id.ingredents);
        making = findViewById(R.id.howto);
        imageView = findViewById(R.id.recipe_image);
        progressBar = findViewById(R.id.progress);
        tvlikesCount = findViewById(R.id.likesCount);
        btngivelike = findViewById(R.id.btnGiveLike);
        tvRatingsCount = findViewById(R.id.tvRatingsCount);
        tvViewCount = findViewById(R.id.tvViewsCount);
        tvcommentsCount = findViewById(R.id.commentsCount);

        mDatabaseRefViews = FirebaseDatabase.getInstance().getReference().child("views");
        mDatabaseRefLikes = FirebaseDatabase.getInstance().getReference().child("likes");
        mDatabaseRefRatings = FirebaseDatabase.getInstance().getReference().child("Ratings");
        mDatabaseRefComments = FirebaseDatabase.getInstance().getReference().child("Comments");

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
                mDatabaseRefRatings.child(key).child(UserId).setValue(rating);
            }).setNegativeButton("Cancel", (dialog, which) -> {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            });
            builder.show();

        });

        if (getIntent() != null) {

            name = getIntent().getExtras().get("name").toString();
            ing = getIntent().getExtras().get("ing").toString();
            make = getIntent().getExtras().get("make").toString();
            key = getIntent().getExtras().get("key").toString();
            url = getIntent().getExtras().get("url").toString();
            ingredents.setText(ing);
            making.setText(make);
            getSupportActionBar().setTitle(name);
            mDatabaseRefViews.child(key).child(UserId).setValue(true);

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


            // Retrive all likes
            mDatabaseRefLikes.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(UserId)) {
                        btngivelike.setText("LIKED");
                        tvlikesCount.setText("" + snapshot.getChildrenCount());
                    } else {
                        btngivelike.setText("LIKE");
                        tvlikesCount.setText("" + snapshot.getChildrenCount());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            // Retrive all RAtings
            mDatabaseRefRatings.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        tvRatingsCount.setText(snapshot.getChildrenCount() + " Ratings");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            // Retrive all Views
            mDatabaseRefViews.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        tvViewCount.setText("" + snapshot.getChildrenCount());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            // Retrive all Comments count
            mDatabaseRefComments.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        tvcommentsCount.setText("" + snapshot.getChildrenCount());
                    }else{
                        tvcommentsCount.setText("0");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {

                    AdLoader adLoader = new AdLoader.Builder(RecipeDetails.this, getString(R.string.detail_page_native_ad))
                            .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                                @Override
                                public void onNativeAdLoaded(NativeAd NativeAd) {
                                    // Show the ad.
                                    NativeTemplateStyle styles = new
                                            NativeTemplateStyle.Builder().build();

                                    TemplateView template = findViewById(R.id.native_ad);
                                    template.setStyles(styles);
                                    template.setNativeAd(NativeAd);
                                }
                            })
                            .withAdListener(new AdListener() {
                                @Override
                                public void onAdFailedToLoad(LoadAdError adError) {
                                    // Handle the failure by logging, altering the UI, and so on.
                                }
                            })
                            .withNativeAdOptions(new NativeAdOptions.Builder()
                                    // Methods in the NativeAdOptions.Builder class can be
                                    // used here to specify individual options settings.
                                    .build())
                            .build();
                    adLoader.loadAd(new AdRequest.Builder().build());

                }
            });




        }



        }








    @Override
    protected void onStart() {
        super.onStart();


    }

    public void givelike(View view) {
        if (showingFirst == true) {
            mDatabaseRefLikes.child(key).child(UserId).setValue(true);
            showingFirst = false;
            btngivelike.setText("Liked");

        } else {
            mDatabaseRefLikes.child(key).child(UserId).removeValue();
            showingFirst = true;
            btngivelike.setText("Like");

        }
    }

    public void giveComment(View view) {
        Intent gotoCommentsActivity = new Intent(RecipeDetails.this, CommentsActivity.class);
        gotoCommentsActivity.putExtra("key", key);
        gotoCommentsActivity.putExtra("name", name);
        startActivity(gotoCommentsActivity);
        //     Toast.makeText(this, "Comment Clicked", Toast.LENGTH_SHORT).show();
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