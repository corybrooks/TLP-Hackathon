package com.example.rickjames.eraticators.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RickJames on 10/5/2017.
 */

public class Rat implements Parcelable {


    private String name, date, type, zip, address, city, borough, latitude, longitude;

    /**
     * Blank constructor, only necessary for firebase.
     */
    public Rat() { }

    /**
     * Constructor for a new Rat
     * @param name rat's key
     * @param date date created
     * @param type address type
     * @param zip address zip
     * @param address address of the rat
     * @param city city rat is in
     * @param borough borough rat is in
     * @param latitude latitude position of rat
     * @param longitude longitude postion of rat
     */
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

    /**
     *
     * @param name the rat's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param date the rat's created date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param type the rat's address type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param zip the rat's address zip code
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @param address the rat's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param city the rat's city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param borough the rat's borough
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     *
     * @param latitude the rat's latitude position
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @param longitude the rat's longitude position
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return the rat's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the rat's date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return the rat's address type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the rat's address zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @return the rat's address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return the rat's city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return the rat's borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     *
     * @return the rat's latitude position
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @return the rat's longitude position
     */
    public String getLongitude() {
        return longitude;
    }

    public int describeContents() {
        return 0;
    }

    /**
     * Writes all the rat data to a parcel
     *
     * @param out the parcel written to
     * @param flags the flags
     */
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

    /**
     * Creates the parcel for the rat, allows you to get a Rat array
     */
    public static final Parcelable.Creator<Rat> CREATOR
            = new Parcelable.Creator<Rat>() {
        public Rat createFromParcel(Parcel in) {
            return new Rat(in);
        }

        public Rat[] newArray(int size) {
            return new Rat[size];
        }
    };

    /**
     * Lets the parcel be read from to retrieve rat data
     *
     * @param in the parcel to read from
     */
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
