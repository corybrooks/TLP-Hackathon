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

public class RatActivity extends AppCompatActivity {

//    key, date, type, zip, address, city, borough, latitude, Longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat);

        TextView name = findViewById(R.id.Name);
        TextView date = findViewById(R.id.Date);
        TextView type = findViewById(R.id.Type);
        TextView zip = findViewById(R.id.Zip);
        TextView address = findViewById(R.id.Address);
        TextView city = findViewById(R.id.City);
        TextView borough = findViewById(R.id.Borough);
        TextView latitude = findViewById(R.id.Latitude);
        TextView longitude = findViewById(R.id.Longitude);

        Button map = findViewById(R.id.MapButton);

        //TextView nameTag = findViewById(R.id.NameTag);
        //Commented out to avoid unnecessary warnings
        //nameTag.setText("Name: ");
        Rat newRat = null;
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            newRat = b.getParcelable("rat");
            if (newRat != null && newRat.getName() != null && newRat.getDate() != null && newRat.getType() != null &&
                    newRat.getZip() != null && newRat.getAddress() != null && newRat.getCity() !=
                    null && newRat.getBorough() != null && newRat.getLatitude() != null &&
                    newRat.getLongitude() != null) {
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
        }
        final Rat finalNewRat = newRat;
        map.setOnClickListener(new View.OnClickListener() {
            //Displays a rat's information as a pin on google maps
            @Override
            public void onClick(View view) {
                try {
                    //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(finalNewRat.getLatitude()), Float.parseFloat(finalNewRat.getLongitude()));
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
