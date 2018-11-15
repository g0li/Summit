package com.lilliemountain.summit;

import android.os.Parcel;
import android.os.Parcelable;

public class Mountains implements Parcelable {
    String name;
    double lat,lng;
    int photo;

    protected Mountains(Parcel in) {
        name = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        photo = in.readInt();
    }

    public static final Creator<Mountains> CREATOR = new Creator<Mountains>() {
        @Override
        public Mountains createFromParcel(Parcel in) {
            return new Mountains(in);
        }

        @Override
        public Mountains[] newArray(int size) {
            return new Mountains[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Mountains(String name, double lat, double lng, int photo) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeInt(photo);
    }
}
