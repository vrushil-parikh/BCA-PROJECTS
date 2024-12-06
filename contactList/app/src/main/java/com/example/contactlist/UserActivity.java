package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactlist.databinding.ActivityMainBinding;
import com.example.contactlist.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    ActivityUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if (intent!=null){
            String name1=intent.getStringExtra("person");
            String name2=intent.getStringExtra("phoneNo");
            String name3=intent.getStringExtra("country");
            int image=intent.getIntExtra("image",R.drawable.cele_img12);

            binding.imageView.setImageResource(image);
            binding.personName.setText(name1);
            binding.phoneNo.setText(name2);
            binding.country.setText(name3);


        }
    }
}