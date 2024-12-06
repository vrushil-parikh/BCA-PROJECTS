package com.example.experiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView txtAutoComplete;
    Button btnSheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAutoComplete=findViewById(R.id.autoCompleteTxt);
        ArrayList<String> languageList=new ArrayList<>();
        languageList.add("C");
        languageList.add("C++");
        languageList.add("C#");
        languageList.add("Java");
        languageList.add("Kotlin");
        languageList.add("Python");
        languageList.add("Html");

        ArrayAdapter<String> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,languageList);
        txtAutoComplete.setAdapter(adapter);

        btnSheet=findViewById(R.id.btnShowSheet);
        btnSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog bottomSheet=new Dialog(MainActivity.this);
                bottomSheet.setContentView(R.layout.bottomsheet_layout);
                bottomSheet.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
                bottomSheet.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                bottomSheet.getWindow().setGravity(Gravity.BOTTOM);
                bottomSheet.getWindow().setWindowAnimations(R.style.dialogAnimation);
                NavigationView nv=bottomSheet.findViewById(R.id.navigationView);
                nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int  id=item.getItemId();
                        switch (id){

                            case R.id.optEdit:
                                Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                                break;
                            case R.id.optShare:
                                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                                break;
                            case R.id.optUpdate:
                                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                                break;
                            case R.id.optPrint:
                                Toast.makeText(MainActivity.this, "Print", Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Something get wrong", Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                        }
                        return true;
                    }
                });
                bottomSheet.show();
            }
        });

    }
}