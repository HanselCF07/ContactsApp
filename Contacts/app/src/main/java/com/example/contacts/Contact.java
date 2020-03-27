package com.example.contacts;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

        private String phoneNumber;
        private String name;
        private String group;

    public Contact(String phoneNumber, String name, String group) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.group = group;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getgroup() {
        return group;
    }

    public void setgroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phoneNumber);
        dest.writeString(this.name);
        dest.writeString(this.group);
    }

    protected Contact(Parcel in) {
        this.phoneNumber = in.readString();
        this.name = in.readString();
        this.group = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
