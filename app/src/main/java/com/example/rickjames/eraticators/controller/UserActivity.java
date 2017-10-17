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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private Button signOutButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ratRef = database
            .getReference().child("RAT_TABLE");

    private FirebaseRecyclerAdapter<Rat, ratViewHolder> firebaseRecyclerAdapter;

    private Query query = FirebaseDatabase.getInstance().getReference().child("RAT_TABLE").limitToFirst(20);

    private FirebaseRecyclerOptions<Rat> options =
            new FirebaseRecyclerOptions.Builder<Rat>()
                    .setQuery(query, Rat.class)
                    .build();

    private String passName, passDate, passType, passZip, passAddress, passCity, passBorough, passLatitude, passLongitude;


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

                final ratViewHolder viewHolder = new ratViewHolder(view);

                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

//                        DatabaseReference passRef = firebaseRecyclerAdapter
//                                .getRef(viewHolder.getAdapterPosition());
//
//                        DatabaseReference nameRef = passRef.child("name");
//                        DatabaseReference dateRef = passRef.child("date");
//                        DatabaseReference typeRef = passRef.child("type");
//                        DatabaseReference zipRef = passRef.child("zip");
//                        DatabaseReference addressRef = passRef.child("address");
//                        DatabaseReference cityRef = passRef.child("city");
//                        DatabaseReference boroughRef = passRef.child("borough");
//                        DatabaseReference latitudeRef = passRef.child("latitude");
//                        DatabaseReference longitudeRef = passRef.child("longitude");
//
//                        nameRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passName = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        dateRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passDate = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        typeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passType = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        zipRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passZip = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        addressRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passAddress = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        cityRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passCity = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        boroughRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passBorough = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        latitudeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passLatitude = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        longitudeRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                passLongitude = (String) dataSnapshot.getValue();
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                        Bundle b = new Bundle();
//                        b.putString("name", passName);
//                        b.putString("date", passDate);
//                        b.putString("type", passType);
//                        b.putString("zip", passZip);
//                        b.putString("address", passAddress);
//                        b.putString("city", passCity);
//                        b.putString("borough", passBorough);
//                        b.putString("latitude", passLatitude);
//                        b.putString("longitude", passLongitude);
//                        Intent intent = new Intent(UserActivity.this, RatActivity.class);
//                        intent.putExtras(b);
//                        startActivity(intent);
                    }
                });
                return viewHolder;
            }

            @Override
            protected void onBindViewHolder(ratViewHolder holder, int position, final Rat model) {
                holder.ratName.setText(model.getName());
                View v = holder.getView();
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle b = new Bundle();
                        b.putParcelable("rat", model);
                        Intent intent = new Intent(UserActivity.this, RatActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });

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
        private View mView;

        public ratViewHolder(View itemView) {
            super(itemView);
            ratName = (TextView) itemView.findViewById(R.id.rat_name);
            mView = itemView;
        }

        public View getView() {
            return mView;
        }
    }
}
