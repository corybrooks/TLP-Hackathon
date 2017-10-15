package com.example.rickjames.eraticators.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RickJames on 10/5/2017.
 */

public class Rat implements Parcelable {
    String key, date, locType, zip, address, city, borough, latitude, longitude;

    public Rat() { }

    public Rat(String key, String date, String locType, String zip, String address,
               String city, String borough, String latitude, String longitude) {
        this.key = key;
        this.date = date;
        this.locType = locType;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocType(String locType) {
        this.locType = locType;
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

    public String getKey() {
        return key;
    }

    public String getDate() {
        return date;
    }

    public String getLocType() {
        return locType;
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
        out.writeString(key);
        out.writeString(date);
        out.writeString(locType);
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
        key = in.readString();
        date = in.readString();
        locType = in.readString();
        zip = in.readString();
        address = in.readString();
        city = in.readString();
        borough = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }
}
