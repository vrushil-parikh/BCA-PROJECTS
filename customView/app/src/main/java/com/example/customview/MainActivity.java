package com.example.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText inPassword,inEmail;
    boolean passwordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        inEmail=findViewById(R.id.inputEmail);
        inPassword=findViewById(R.id.inputPassword);
        inPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=inPassword.getRight()-inPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=inPassword.getSelectionEnd();
                        if (passwordVisible){
                            inPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_hidden,0);
                            inPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else {
                            inPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_show,0);
                            inPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        inPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        
    }
}