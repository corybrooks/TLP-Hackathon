package com.example.rickjames.eraticators.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.Rat;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Locale;

public class RatActivity extends AppCompatActivity {

//    key, date, type, zip, address, city, borough, latitude, Longitude

    private TextView name;
    private TextView date;
    private TextView type;
    private TextView zip;
    private TextView address;
    private TextView city;
    private TextView borough;
    private TextView latitude;
    private TextView longitude;
    private TextView nameTag;
    private Button map;

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat);

        name = (TextView) findViewById(R.id.Name);
        date = (TextView) findViewById(R.id.Date);
        type = (TextView) findViewById(R.id.Type);
        zip = (TextView) findViewById(R.id.Zip);
        address = (TextView) findViewById(R.id.Address);
        city = (TextView) findViewById(R.id.City);
        borough = (TextView) findViewById(R.id.Borough);
        latitude = (TextView) findViewById(R.id.Latitude);
        longitude = (TextView) findViewById(R.id.Longitude);

        map = (Button) findViewById(R.id.MapButton);

        nameTag = (TextView) findViewById(R.id.NameTag);
        nameTag.setText("Name: ");
        Rat newRat = null;
        b = this.getIntent().getExtras();
        if (b != null) {
            newRat = b.getParcelable("rat");
            name.setText(newRat.getName());
            date.setText(newRat.getDate());
            type.setText(newRat.getType());
            zip.setText(newRat.getZip());
            address.setText(newRat.getAddress());
            city.setText(newRat.getCity());
            borough.setText(newRat.getBorough());
            latitude.setText(newRat.getLatitude());
            longitude.setText(newRat.getLongitude());
        }
        final Rat finalNewRat = newRat;
        map.setOnClickListener(new View.OnClickListener() {
            //Displays a rat's information as a pin on google maps
            @Override
            public void onClick(View view) {
                try {
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(finalNewRat.getLatitude()), Float.parseFloat(finalNewRat.getLongitude()));
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + Float.parseFloat(finalNewRat.getLatitude())  +
                            ">,<" + Float.parseFloat(finalNewRat.getLongitude()) + ">?q=<" +
                            Float.parseFloat(finalNewRat.getLatitude())  + ">,<" +
                            Float.parseFloat(finalNewRat.getLongitude()) + ">(Rat: " + finalNewRat.getName() + ") Address: " + finalNewRat.getAddress()));

                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(RatActivity.this, "Error attempting to view rat on map. Are latitude and longitude valid?" , Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
