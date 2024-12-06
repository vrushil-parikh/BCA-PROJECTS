package com.example.livepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
        boolean isLoggedIn=pref.getBoolean("status",false);

        new Handler().postDelayed(()->{
            Intent intent;

            if (isLoggedIn){
                intent=new Intent(SplashActivity.this,HomeActivity.class);
            }
            else{
                intent=new Intent(SplashActivity.this,LoginActivity.class);
            }
            startActivity(intent);
            finish();
        },3000);
    }
}