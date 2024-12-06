package com.example.dynamicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<UserData> {
    public ListAdapter(@NonNull Context context, ArrayList<UserData> info) {

        super(context, R.layout.basic_show, info);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.basic_show,parent,false);
        }
        UserData data=getItem(position);
        TextView basicName=convertView.findViewById(R.id.basicName);
        basicName.setText(data.name);

        return convertView;
    }
}
