package com.example.livepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    Button add;
    EditText username, email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

       DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("Users");

        add=findViewById(R.id.btnRegistration);
        email=findViewById(R.id.inputRegEmail);
        username=findViewById(R.id.inputUsername);
        password=findViewById(R.id.inputRegPassword);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.length()==0)
                {
                    username.setError("Enter the username");
                }
                else if (username.getText().toString().contains(".") ||
                         username.getText().toString().contains("#") ||
                         username.getText().toString().contains("/") ||
                         username.getText().toString().contains("$") ||
                         username.getText().toString().contains("[") ||
                         username.getText().toString().contains("]")){
                    username.setError(".,#,/,$,[,] is not allowed");
                }
                else if (email.length()==0)
                {
                    email.setError("Enter the email");
                }
                else if (password.length()==0)
                {
                    password.setError("Enter the password");
                }
                else
                {
                    try {
                        UserInfo userInfo=new UserInfo(email.getText().toString(),password.getText().toString(),username.getText().toString(),"head");

                        //check if username is available or not
                        Query check_username = userRef.orderByChild("username").equalTo(username.getText().toString());
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()){
                                    username.setError("This is not an unique username");
                                    Toast.makeText(RegistrationActivity.this,"Enter a new username",Toast.LENGTH_SHORT).show();
                                }

                                else{
                                    userRef.child(username.getText().toString()).setValue(userInfo);
                                    Toast.makeText(RegistrationActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent iMAin = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(iMAin);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }catch (Exception e){
                        Toast.makeText(RegistrationActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}