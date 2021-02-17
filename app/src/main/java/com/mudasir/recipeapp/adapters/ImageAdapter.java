package com.mudasir.recipeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.activities.RecipeDetails;
import com.mudasir.recipeapp.models.Recipe;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImagesViewHolder> {
    private Context mContext;
    private List<Recipe> mRecipeList;

    public ImageAdapter(Context mContext, List<Recipe> mRecipeList) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(mContext).inflate(R.layout.single_item_food,parent,false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {

        Recipe recipe=mRecipeList.get(position);
        holder.tvTitle.setText(recipe.getTitle());
        //holder.mRatingBar.setRating(recipe.getRating());
        holder.mRatingBar.setEnabled(false);


        Glide.with(mContext).load(recipe.getImageUrl()).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                holder.mProgress.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.mProgress.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imageView);


        holder.btnViewRecipe.setOnClickListener(v -> {
            Intent intent=new Intent(mContext,RecipeDetails.class);
            intent.putExtra("name",mRecipeList.get(position).getTitle());
            intent.putExtra("url",mRecipeList.get(position).getImageUrl());
            intent.putExtra("ing",mRecipeList.get(position).getIngredents());
            intent.putExtra("key",mRecipeList.get(position).getKey());
            intent.putExtra("make",mRecipeList.get(position).getMaking());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;
        RatingBar mRatingBar;
        Button btnViewRecipe;
        ProgressBar mProgress;


        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image);
            tvTitle=itemView.findViewById(R.id.tv_title);
            mRatingBar=itemView.findViewById(R.id.ratingBar);
            btnViewRecipe=itemView.findViewById(R.id.btnView);
            mProgress=itemView.findViewById(R.id.single_item_progress);


        }
    }
}
