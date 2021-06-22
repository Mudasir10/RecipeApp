package com.mudasir.recipeapp.activities;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mudasir.recipeapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AcountDetails extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mCurrentuser;
    TextView usernname,userId,UserEmail;
    CircleImageView imageView;
    private ProgressBar progressBar;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_details);

        getSupportActionBar().setTitle("Profile Details");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        imageView=findViewById(R.id.profile_image);
        userId=findViewById(R.id.user_id);
        UserEmail=findViewById(R.id.user_email);
        usernname=findViewById(R.id.user_name);
        progressBar=findViewById(R.id.progress);
        btnLogout=findViewById(R.id.btnLogout);

        mAuth=FirebaseAuth.getInstance();
        mCurrentuser=mAuth.getCurrentUser();

        if (mCurrentuser!=null){

            Glide.with(this)
                    .load(mCurrentuser.getPhotoUrl())
                    .listener(new RequestListener<Uri, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                           progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
            UserEmail.setText("Email : "+mCurrentuser.getEmail());
            usernname.setText("Name : "+mCurrentuser.getDisplayName());
            userId.setText("id : "+mCurrentuser.getUid());

        }

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(this,
                    SignIn.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });



    }
}