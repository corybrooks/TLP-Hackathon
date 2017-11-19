package com.example.rickjames.eraticators.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.Rat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity  {

    private EditText inputtedEmail;
    private EditText inputtedPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button loginButton;
    private Button signupButton;
    private Button forgotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.SignIn);
        final String userEmail;
        final String userPassword;
        forgotButton = (Button) findViewById(R.id.ForgotPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputtedEmail.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this,"Error logging in. Must input username and password.", Toast.LENGTH_SHORT).show();
                } else {
                    String userEmail = inputtedEmail.getText().toString();
                    String userPassword = inputtedPassword.getText().toString();
                    signIn(userEmail, userPassword);
                }

            }
        });

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputtedEmail.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this,"Unable to send password reset email. Is a valid email inputted?", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(inputtedEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });
                Toast.makeText(LoginActivity.this,"Password reset email sent to "+ inputtedEmail.getText().toString(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String TAG = null;
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        inputtedEmail = (EditText) findViewById(R.id.inputtedEmail);
        inputtedPassword = (EditText) findViewById(R.id.inputtedPassword);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Authenticates the users account using the email and password.
     * If information is correct it signs in the user in and calls the
     * UserActivity.
     * @param email The users email.
     * @param password The users password.
     */
    public void signIn(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            finish();
                            Toast.makeText(LoginActivity.this, "Welcome "+ email, Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }

                        // ...
                    }
                });
    }
}
