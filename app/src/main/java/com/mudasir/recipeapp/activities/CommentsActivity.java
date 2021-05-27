package com.mudasir.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mudasir.recipeapp.R;
import com.mudasir.recipeapp.adapters.CommentsAdapter;
import com.mudasir.recipeapp.adapters.ImageAdapter;
import com.mudasir.recipeapp.models.Comments;
import com.mudasir.recipeapp.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextInputEditText et_comment;


    private List<Comments> mRecipeCommentsList;
    private DatabaseReference mDatabaseRefComments;
    private ImageButton btnSendComment;
    private String key;
    private String id;
     FirebaseAuth mAuth;
     FirebaseUser mCurrentuser;
    private String recipe_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        init();
        RetrieveMessages();


    }

    private void RetrieveMessages() {


        Query query=  mDatabaseRefComments.child(key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mRecipeCommentsList.clear();
                for (DataSnapshot Snapshot :snapshot.getChildren()) {
                    Comments comments=Snapshot.getValue(Comments.class);
                    mRecipeCommentsList.add(comments);
                }
                mAdapter=new CommentsAdapter(mRecipeCommentsList,CommentsActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void init() {

        if (getIntent()!=null){
            key = getIntent().getExtras().get("key").toString();
            recipe_name = getIntent().getExtras().get("name").toString();
        }

        getSupportActionBar().setTitle(recipe_name);

        mAuth=FirebaseAuth.getInstance();
        mCurrentuser=mAuth.getCurrentUser();

        mRecipeCommentsList=new ArrayList<>();
        et_comment=findViewById(R.id.et_comment);
        btnSendComment=findViewById(R.id.btnsendcomment);

        mDatabaseRefComments= FirebaseDatabase.getInstance().getReference().child("Comments");

        mRecyclerView=findViewById(R.id.rvComments);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        btnSendComment.setOnClickListener(v -> {

           String comment= et_comment.getEditableText().toString();

            if (!comment.isEmpty()){
                String randomId= mDatabaseRefComments.push().getKey();

                DatabaseReference mCommentsRef= mDatabaseRefComments.child(key);

                Comments comments=new Comments(mCurrentuser.getUid(),mCurrentuser.getDisplayName(),comment);
                Map<String, Object> postValues = comments.toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(randomId, postValues);
                mCommentsRef.updateChildren(childUpdates);

                Toast.makeText(this, "Comment Posted Successfully", Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(CommentsActivity.this, "Can not be null.", Toast.LENGTH_SHORT).show();
            }

        });

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