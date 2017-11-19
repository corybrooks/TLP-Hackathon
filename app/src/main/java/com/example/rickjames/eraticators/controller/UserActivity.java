package com.example.rickjames.eraticators.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.Rat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;




public class UserActivity extends AppCompatActivity {

    private EditText startDateText;
    private EditText endDateText;

    private FirebaseRecyclerAdapter<Rat, ratViewHolder> firebaseRecyclerAdapter;

    private final Query query = FirebaseDatabase.getInstance().getReference().child("RAT_TABLE").limitToLast(30);

    private final FirebaseRecyclerOptions<Rat> options =
            new FirebaseRecyclerOptions.Builder<Rat>()
                    .setQuery(query, Rat.class)
                    .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

//        String x = getIntent().getStringExtra("newFirst");
//        if (getIntent().getStringExtra("newFirst").equals("false")) {
//                FirebaseDatabase.getInstance().getReference().child("RAT_TABLE").limitToLast(5);
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView mRecyclerView = findViewById(R.id.RatList);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        Button signOutButton = findViewById(R.id.SignOut);
        Button addRatButton = findViewById(R.id.AddRat);
        Button updateRatsButton = findViewById(R.id.updateRats);

        Button startDateButton = findViewById(R.id.startDate);
        startDateText = findViewById(R.id.startDateText);
        Button endDateButton = findViewById(R.id.endDate);
        endDateText = findViewById(R.id.endDateText);

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

        addRatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, AddRatActivity.class);
                finish();
                startActivity(intent);
            }
        });

        updateRatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, GraphActivity.class);
                finish();
                startActivity(intent);
            }
        });



        final Calendar myCalendar = Calendar.getInstance();
        final String[] whichDate = {null};
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                if (whichDate[0].equals("START")) {
                    startDateText.setText(sdf.format(myCalendar.getTime()));
                }
                else {
                    endDateText.setText(sdf.format(myCalendar.getTime()));
                }

            }

        };
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichDate[0] = "START";
                new DatePickerDialog(UserActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichDate[0] = "END";
                new DatePickerDialog(UserActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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


    /**
     * View holder for rat class. This is used by the firebaseRecyclerAdapter to
     * bind the rat_name to the cards in the RecyclerView.
     */
    public static class ratViewHolder extends RecyclerView.ViewHolder {
        private final TextView ratName;
        private final View mView;

        public ratViewHolder(View itemView) {
            super(itemView);
            ratName = itemView.findViewById(R.id.rat_name);
            mView = itemView;
        }

        /**
         * This is necessary in order to receive the view and pass the model to the new view!!
         * @return the view
         */
        public View getView() {
            return mView;
        }
    }


}
