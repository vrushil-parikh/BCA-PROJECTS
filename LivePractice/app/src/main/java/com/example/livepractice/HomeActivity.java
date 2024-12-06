package com.example.livepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class HomeActivity extends AppCompatActivity {
    boolean isEdit=false;
    int editIndex=0;
    ListView expList;
    EditText editParticular,editAmt;
    Button btnAddExp;
    TextView totalExp;
    int totExp=0;
    ArrayList<expInfo> expArray=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
        expList=findViewById(R.id.listExp);
        editParticular=findViewById(R.id.editParticular);
        editAmt=findViewById(R.id.editAmt);
        totalExp=findViewById(R.id.txtTotalExp);
        btnAddExp=findViewById(R.id.btn_addExp);

        editParticular.requestFocus();
        String username=pref.getString("username",null);

        ArrayList<String> childKeys=new ArrayList<>();

        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(username).child("userExpData")
                        .child(String.valueOf(Calendar.getInstance().getTime().getYear()))
                .child(String.valueOf(Calendar.getInstance().getTime().getMonth()+1));
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    Iterable<DataSnapshot> iterable=task.getResult().getChildren();
                    Iterator<DataSnapshot> iterator=iterable.iterator();
                    totExp=0;
                    while (iterator.hasNext()){
                        DataSnapshot child=iterator.next();
                        childKeys.add(child.getKey());
                        expInfo e=child.getValue(expInfo.class);
                        expArray.add(e);
                        setTotExp();
                    }

                }
                else{
                    Log.d("firebase","Error in reading the data");
                }
            }
        });
        ExpListAdapter adapter=new ExpListAdapter(HomeActivity.this, expArray);
        expList.setAdapter(adapter);


        btnAddExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editParticular.length()==0)
                    editParticular.setError("enter the particular");
                else if ((Integer.parseInt(editAmt.getText().toString()))==0)
                    editAmt.setError("Enter the amount");
                else
                {
                    int expAmt = Integer.parseInt(editAmt.getText().toString());
                    expInfo expInfo = new expInfo(editParticular.getText().toString(), expAmt);
                    if (isEdit)
                    {
                        expArray.remove(editIndex);
                        expArray.add(editIndex,expInfo);
                        dbRef.child(childKeys.get(editIndex)).setValue(expInfo);
                        editIndex=0;
                        isEdit=false;
                    }
                    else {

                        expArray.add(expInfo);
                        String key = dbRef.push().getKey();
                        childKeys.add(key);
                        dbRef.child(key).setValue(expInfo);

                    }
                    setTotExp();
                    adapter.notifyDataSetChanged();
                    editParticular.setText("");
                    editAmt.setText("");
                }
            }
        });

        expList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog editInfo=new Dialog(HomeActivity.this);
                editInfo.setContentView(R.layout.exp_info_edit_dialog);
                Window window=editInfo.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                WindowManager.LayoutParams wlp=window.getAttributes();
                wlp.gravity=Gravity.BOTTOM;
                window.setAttributes(wlp);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                   window.setGravity(view.getForegroundGravity());
                }
                editInfo.findViewById(R.id.dialogDelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbRef.child(childKeys.get(i)).removeValue();
                        childKeys.remove(i);
                        expArray.remove(i);
                        adapter.notifyDataSetChanged();
                        setTotExp();
                        editInfo.dismiss();
                    }
                });
                editInfo.findViewById(R.id.dialogEdit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        expInfo expInfo= expArray.get(i);
                        editParticular.setText(expInfo.getParticular());
                        editAmt.setText(String.valueOf(expInfo.getAmount()));
                        editParticular.requestFocus();
                        isEdit=true;
                        editIndex=i;
                        setTotExp();
                        editInfo.dismiss();
                    }
                });
                editInfo.show();
            }
        });
    }
    public void setTotExp(){
        totExp=0;
        for (expInfo e:expArray) {
            totExp=totExp+e.getAmount();
        }
        totalExp.setText(String.valueOf(totExp));
    }
}