package com.example.owner.cafm.Objects;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private long type;
    private String username;

    public long getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.type);
        dest.writeString(this.username);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.type = in.readLong();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
