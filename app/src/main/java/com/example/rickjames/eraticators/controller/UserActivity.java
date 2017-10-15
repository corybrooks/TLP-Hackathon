package com.example.rickjames.eraticators.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.Rat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    private Button signOutButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference ratRef = FirebaseDatabase.getInstance().getReference().child("RAT_TABLE");

    private LinearLayoutManager mLinearLayoutManager;

    private FirebaseRecyclerAdapter<Rat, ratViewHolder> firebaseRecyclerAdapter;

    private Query query = FirebaseDatabase.getInstance().getReference().child("RAT_TABLE").limitToFirst(5);


    private FirebaseRecyclerOptions<Rat> options =
            new FirebaseRecyclerOptions.Builder<Rat>()
                    .setQuery(query, Rat.class)
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mRecyclerView = (RecyclerView) findViewById(R.id.RatList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        signOutButton = (Button) findViewById(R.id.SignOut);

        firebaseRecyclerAdapter =  new FirebaseRecyclerAdapter<Rat, ratViewHolder>(options) {
            @Override
            public ratViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.rat_row, parent, false);

                return new ratViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ratViewHolder holder, int position, Rat model) {
                holder.ratName.setText(model.getDate());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class ratViewHolder extends RecyclerView.ViewHolder {
        private TextView ratName;

        public ratViewHolder(View itemView) {
            super(itemView);
            ratName = (TextView) itemView.findViewById(R.id.rat_name);
        }
    }
}
