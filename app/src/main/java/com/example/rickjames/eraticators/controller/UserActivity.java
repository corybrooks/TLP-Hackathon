package com.example.rickjames.eraticators.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rickjames.eraticators.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    private Button signOutButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        signOutButton = (Button) findViewById(R.id.SignOut);

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
}
