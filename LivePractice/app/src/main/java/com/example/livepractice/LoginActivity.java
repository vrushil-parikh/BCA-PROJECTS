package com.example.livepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername,loginPassword;
    Button btnLogin;
    TextView linkRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        loginUsername=findViewById(R.id.inputLoginUsername);
        loginPassword=findViewById(R.id.inputLoginPassword);
        btnLogin=findViewById(R.id.btnLogin);
        linkRegister=findViewById(R.id.txtLinkRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                {
                    if (loginUsername.length()==0)
                        loginUsername.setError("Enter the username");
                    else if (loginPassword.length()==0)
                        loginPassword.setError("Enter the password");
                    else
                    {
                        try {

                            final String username_s = loginUsername.getText().toString();
                            final String password_s= loginPassword.getText().toString();

                            DatabaseReference dbRef=FirebaseDatabase.getInstance().getReference("Users");
                            Query check_username = dbRef.orderByChild("username").equalTo(username_s);

                            check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if (snapshot.exists()){
                                        String check_password=snapshot.child(username_s).child("password").getValue(String.class);
                                        if(check_password.equals(password_s)){
                                            String userType=snapshot.child(username_s).child("userType").getValue(String.class );
                                            SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                                            SharedPreferences.Editor editor=pref.edit();
                                            editor.putBoolean("status",true);
                                            editor.putString("username",loginUsername.getText().toString());
                                            editor.putString("userType",userType);
                                            editor.apply();

                                            Intent iHome=new Intent(LoginActivity.this,HomeActivity.class);
                                            startActivity(iHome);
                                            finish();
                                        }
                                        else
                                            loginPassword.setError("Wrong password");
                                    }
                                    else
                                        loginUsername.setError("User does not exist");

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }catch (Exception e){
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }else
                    Toast.makeText(LoginActivity.this, "No Network Connection available", Toast.LENGTH_SHORT).show();

            }
        });

        linkRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent iRegister=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(iRegister);
            }
        });
    }

}