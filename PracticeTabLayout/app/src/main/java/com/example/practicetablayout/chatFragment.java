package com.example.practicetablayout;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class chatFragment extends Fragment {
    TextView dateShow;
    public chatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        int day,month,year;
        day=0;
        dateShow=view.findViewById(R.id.dateShow);
        dateShow.setShowSoftInputOnFocus(false);
        dateShow.setOnClickListener(view1 -> {
            Dialog date=new Dialog(getContext());
            date.getWindow().setGravity(Gravity.BOTTOM);
            date.setCancelable(false);
            date.setContentView(R.layout.layout_date);
            date.show();
            DatePicker dp=date.findViewById(R.id.datePicker);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dp.setOnDateChangedListener((datePicker, i, i1, i2) -> dateShow.setText(i2+"/"+(i1+1)+"/"+i));
                date.findViewById(R.id.btnDateSave).setOnClickListener(view2 -> {
                    date.dismiss();
                });
            }
        });
        return view;
    }
}