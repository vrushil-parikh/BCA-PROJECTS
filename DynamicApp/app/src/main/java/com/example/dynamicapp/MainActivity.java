package com.example.dynamicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listName=findViewById(R.id.listviewNames);

        String url="https://jsonplaceholder.typicode.com/users";
        ArrayList<String> arrName=new ArrayList<>();

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("response",response.toString());


                        //PARSING JASON RESPONSE

                        ArrayList<UserData> info=new ArrayList<>();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject obj=response.getJSONObject(i);
                                UserData data =new UserData();
                                data.id=obj.getString("id");
                                data.name=obj.getString("name");
                                data.username=obj.getString("username");
                                data.email=obj.getString("email");
                                JSONObject objAddress=obj.getJSONObject("address");
                                data.address=objAddress.getString("suite")+", "+objAddress.getString("street")+", "+objAddress.getString("city");
                                data.phone=obj.getString("phone");
                                data.website=obj.getString("website");
                                JSONObject objCompany=obj.getJSONObject("company");
                                data.company=objCompany.getString("name");
                                info.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ListAdapter adapter=new ListAdapter(MainActivity.this,info);
                        listName.setAdapter(adapter);
                        listName.setClickable(true);
                        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(MainActivity.this,FeedData.class);
                                intent.putExtra("id",info.get(i).id);
                                intent.putExtra("name",info.get(i).name);
                                intent.putExtra("username",info.get(i).username);
                                intent.putExtra("email",info.get(i).email);
                                intent.putExtra("address",info.get(i).address);
                                intent.putExtra("phone",info.get(i).phone);
                                intent.putExtra("website",info.get(i).website);
                                intent.putExtra("company",info.get(i).company);
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(MainActivity.this, "Error in showing data", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}