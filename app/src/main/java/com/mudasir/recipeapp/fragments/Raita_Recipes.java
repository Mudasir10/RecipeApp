package com.mudasir.recipeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.adapters.ImageAdapter;
import com.mudasir.recipeapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;


public class Raita_Recipes extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Recipe> mRecipeList;
    private DatabaseReference mDatabaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_raita__recipes, container, false);

        init(root);
        RetriveFromFirebaseDatabase();


        return root;
    }

    private void init(View root) {
        mRecipeList=new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Recipes");
        mRecyclerView=root.findViewById(R.id.rv_raita_recipes);
        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void RetriveFromFirebaseDatabase() {

        Query query=  mDatabaseRef.orderByChild("category").equalTo("Raita Recipes");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mRecipeList.clear();
                for (DataSnapshot Snapshot :snapshot.getChildren()) {
                    Recipe recipe=Snapshot.getValue(Recipe.class);
                    mRecipeList.add(recipe);
                }
                mAdapter=new ImageAdapter(getContext(),mRecipeList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}