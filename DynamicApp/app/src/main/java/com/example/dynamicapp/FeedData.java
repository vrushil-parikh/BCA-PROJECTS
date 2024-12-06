package com.example.dynamicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FeedData extends AppCompatActivity {
    TextView infoId,infoName,infoUsername,infoEmail,infoAddress,infoPhone,infoWebsite,infoCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_data);

        infoId=findViewById(R.id.infoId);
        infoName=findViewById(R.id.infoName);
        infoUsername=findViewById(R.id.infoUsername);
        infoEmail=findViewById(R.id.infoEmail);
        infoAddress=findViewById(R.id.infoAddress);
        infoPhone=findViewById(R.id.infoPhone);
        infoWebsite=findViewById(R.id.infoWebsite);
        infoCompany=findViewById(R.id.infoCompany);

        String id,name,username,email,address,phone,website,company;
        id="ID : "+getIntent().getStringExtra("id");
        name="NAME : "+getIntent().getStringExtra("name");
        username="USERNAME : "+getIntent().getStringExtra("username");
        email="EMAIL : "+getIntent().getStringExtra("email");
        address="ADDRESS : "+getIntent().getStringExtra("address");
        phone="PHONE : "+getIntent().getStringExtra("phone");
        website="WEBSITE : "+getIntent().getStringExtra("website");
        company="COMPANY NAME : "+getIntent().getStringExtra("company");
        infoId.setText(id);
        infoName.setText(name);
        infoUsername.setText(username);
        infoEmail.setText(email);
        infoAddress.setText(address);
        infoPhone.setText(phone);
        infoWebsite.setText(website);
        infoCompany.setText(company);
    }
}