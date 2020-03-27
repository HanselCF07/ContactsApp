package com.example.contacts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    Context context;
    int layaoutResourceID;

    ArrayList<Contact> data;


    static class ContactoHolder {
        TextView phoneNumber;
        TextView name;
        TextView group;
    }

    public  ContactAdapter(Context context, int layaoutResourceID, ArrayList<Contact> data) {
        super(context,layaoutResourceID,data);

        this.context = context;
        this.layaoutResourceID = layaoutResourceID;
        this.data = data;
    }

    public View getView(int position,View convertView, ViewGroup parent) {

        View row = convertView;

        ContactoHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layaoutResourceID, parent,false);
            holder = new ContactoHolder();
            holder.phoneNumber = (TextView) row.findViewById(R.id.text2);
            holder.name = (TextView) row.findViewById(R.id.text1);
            holder.group = (TextView) row.findViewById(R.id.text3);
            row.setTag(holder);


        }else {
            holder = (ContactoHolder)row.getTag();
        }

        Contact contact = data.get(position);
        holder.name.setText(contact.getname());
        holder.phoneNumber.setText(contact.getphoneNumber());
        holder.group.setText(contact.getgroup());


        return row;
    }

}
