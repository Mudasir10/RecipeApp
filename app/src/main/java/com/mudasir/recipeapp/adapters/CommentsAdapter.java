package com.mudasir.recipeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.models.Comments;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {


    private List<Comments> mRecipeCommentsList;
    private Context mContext;

    public CommentsAdapter(List<Comments> mRecipeCommentsList, Context mContext) {
        this.mRecipeCommentsList = mRecipeCommentsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(mContext).inflate(R.layout.single_comment_layout,parent,false);
        return new CommentsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {

        Comments comments= mRecipeCommentsList.get(position);
        holder.tvUserName.setText(comments.getUserName());
        holder.tvcm.setText(comments.getComment());
    }

    @Override
    public int getItemCount() {
        return mRecipeCommentsList.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView tvcm,tvUserName;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcm=itemView.findViewById(R.id.tvComment);
            tvUserName=itemView.findViewById(R.id.tvUserName);
        }
    }
}
