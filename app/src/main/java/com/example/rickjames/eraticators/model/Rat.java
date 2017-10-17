package com.example.rickjames.eraticators.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RickJames on 10/5/2017.
 */

public class Rat implements Parcelable {


    private String name, date, type, zip, address, city, borough, latitude, longitude;

    public Rat() { }

    public Rat(String name, String date, String type, String zip, String address,
               String city, String borough, String latitude, String longitude) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getBorough() {
        return borough;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(date);
        out.writeString(type);
        out.writeString(zip);
        out.writeString(address);
        out.writeString(city);
        out.writeString(borough);
        out.writeString(latitude);
        out.writeString(longitude);
    }

    public static final Parcelable.Creator<Rat> CREATOR
            = new Parcelable.Creator<Rat>() {
        public Rat createFromParcel(Parcel in) {
            return new Rat(in);
        }

        public Rat[] newArray(int size) {
            return new Rat[size];
        }
    };

    private Rat(Parcel in) {
        name = in.readString();
        date = in.readString();
        type = in.readString();
        zip = in.readString();
        address = in.readString();
        city = in.readString();
        borough = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }
}
