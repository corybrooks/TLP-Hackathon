package com.example.rickjames.eraticators.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rickjames.eraticators.R;
import com.example.rickjames.eraticators.model.Rat;

import org.w3c.dom.Text;

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

        nameTag = (TextView) findViewById(R.id.NameTag);
        nameTag.setText("Name: ");

        b = this.getIntent().getExtras();
        if (b != null) {
            Rat newRat = b.getParcelable("rat");
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
}
