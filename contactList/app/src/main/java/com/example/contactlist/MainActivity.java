package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.contactlist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int image[]={R.drawable.cele_img12,R.drawable.cele_img_11,R.drawable.celebrity,R.drawable.digi_launcher,
                R.drawable.facebook,R.drawable.flag,R.drawable.google,R.drawable.political,R.drawable.sports};
        String names[]={"Kushtibaz","AnnuKangana","VickeyKet","DigiNews","FaceBook","India","Google","Narendra Modi","KL Rahul"};
        String lastMessage[]={"Heyy","What's up","Good Morning","What are you doing?","Hy, here you go!","God blese you","Let's go","In a meeting","Dinner tonight?"};
        String msgTime []={"08:25","04:45","11:23","12:52","01:00","09:05","05:40","12:12","09:07","07:59"};
        String phoneNo[]={"9825352506","9428661387","9173729046","9427642175","9408623619","8877665544","9825345874","9510866113","7865453251"};
        String country[]={"Canada","New York","France","Paris","China","Sri Lanka","Abu Dhabi","Germany","India",};

        ArrayList<User> userArrayList=new ArrayList<>();
        for (int i=0;i<image.length;i++)
        {
            User user=new User(names[i],lastMessage[i],msgTime[i],phoneNo[i],country[i],image[i]);
            userArrayList.add(user);
        }
        ListAdapter listAdapter=new ListAdapter(MainActivity.this,userArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),UserActivity.class);
                intent.putExtra("person",names[position]);
                intent.putExtra("phoneNo",phoneNo[position]);
                intent.putExtra("country",country[position]);
                intent.putExtra("image",image[position]);
                startActivity(intent);

            }
        });
    }
}