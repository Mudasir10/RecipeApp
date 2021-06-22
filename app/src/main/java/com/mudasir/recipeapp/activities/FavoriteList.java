package com.mudasir.recipeapp.activities;

import static com.mudasir.recipeapp.activities.MainActivity.favoriteDatabase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.adapters.ImageAdapter;
import com.mudasir.recipeapp.models.Recipe;
import com.mudasir.recipeapp.models.RecipeModelForRoom;

import java.util.ArrayList;
import java.util.List;

public class FavoriteList extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<RecipeModelForRoom> list;
    List<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        getSupportActionBar().setTitle("favorite List");

        setUpRecyclerView();

       list=new ArrayList<>();
       recipeList=new ArrayList<>();


       list.clear();
       recipeList.clear();
       list= favoriteDatabase.favDao().getFavoriteData();
        for (int i = 0; i < list.size(); i++) {
            RecipeModelForRoom recipeModelForRoom= list.get(i);
            Recipe recipe =new Recipe(recipeModelForRoom.getKey(),recipeModelForRoom.getTitle(),recipeModelForRoom.getIngredents(),recipeModelForRoom.getMaking(),recipeModelForRoom.getImageUrl(),recipeModelForRoom.getCategory());
            recipeList.add(recipe);
        }
        mAdapter=new ImageAdapter(this,recipeList);
        recyclerView.setAdapter(mAdapter);


    }

    private void setUpRecyclerView() {
        recyclerView=findViewById(R.id.rv_favoritelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
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