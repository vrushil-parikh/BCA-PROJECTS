package com.example.livepractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.protobuf.Internal;

import java.util.ArrayList;

public class ExpListAdapter extends ArrayAdapter<expInfo> {
    public ExpListAdapter(@NonNull Context context, ArrayList<expInfo> expList) {
        super(context,R.layout.exp_listitem_layout, expList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        expInfo expInfo=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.exp_listitem_layout,parent,false);
        }
        TextView txtListExpDetail=convertView.findViewById(R.id.txtListExpDetail);
        TextView txtListExpAmt=convertView.findViewById(R.id.txtListExpAmt);

        txtListExpDetail.setText(expInfo.getParticular());
        txtListExpAmt.setText(String.valueOf(expInfo.getAmount()));

        return convertView;
    }
}
