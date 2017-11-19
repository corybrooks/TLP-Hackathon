package com.example.rickjames.eraticators.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.User;
import com.example.rickjames.eraticators.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText inputtedEmail;
    private EditText inputtedPassword;
    private Spinner userSpinner;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference userRef = database.getReference("USER_TABLE");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userSpinner = findViewById(R.id.userType);
        Button signUpButton = findViewById(R.id.SignUp);
        //final String userEmail;
        //final String userPassword;



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputtedEmail.getText().toString().equals("") || inputtedPassword.getText().toString().equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Email and/or password cannot be empty.",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String userEmail = inputtedEmail.getText().toString();
                    String userPassword = inputtedPassword.getText().toString();
                    signUp(userEmail, userPassword);
                }
            }
        });

        ArrayAdapter<UserType> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String TAG = "User";
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        inputtedEmail = findViewById(R.id.inputtedEmail);
        inputtedPassword = findViewById(R.id.inputtedPassword);
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
     * Creates the new user account with email and password.
     * @param email The new users email.
     * @param password The new users password
     */
    private void signUp(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            createUser(email, password);
                            finish();
                        }
                    }
                });
    }

    /**
     * Creates an instance of the user object.
     * @param email The new users email.
     * @param password The new users password.
     */
    private void createUser(String email, String password) {
        final String userName;
        final UserType newUserType;
        EditText inputtedName = findViewById(R.id.Names);
        userName = inputtedName.getText().toString();
        if (userName.equals("")) {
            Toast.makeText(RegistrationActivity.this, "Name cannot be empty.",
                    Toast.LENGTH_SHORT).show();
        } else {
            userSpinner = findViewById(R.id.userType);
            newUserType = (UserType) userSpinner.getSelectedItem();
            User newUser = new User(userName, newUserType, email, password);
            addUserToDatabase(newUser);
        }
    }

    /**
     * Adds the new user's information to the database.
     * @param newUser The instance of the new user.
     */
    private void addUserToDatabase(User newUser) {
        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
        if (userID != null) {
            DatabaseReference childRef = userRef.child(userID.getUid());

            DatabaseReference uidChildRef1 = childRef.child("userName");
            uidChildRef1.setValue(newUser.getName());

            DatabaseReference uidChildRef2 = childRef.child("userType");
            uidChildRef2.setValue(newUser.getUser().toString());

            DatabaseReference uidChildRef3 = childRef.child("userEmail");
            uidChildRef3.setValue(newUser.getEmail());

            DatabaseReference uidChildRef4 = childRef.child("userPassword");
            uidChildRef4.setValue(newUser.getPassword());

            if (newUser.getUser().toString().equals("Admin")) {
                Toast.makeText(RegistrationActivity.this, "Admin registration successful!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistrationActivity.this, "User registration successful!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
