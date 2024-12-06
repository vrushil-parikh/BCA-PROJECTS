package com.example.instagram;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class Home extends Fragment{
private RecyclerView storyBar;
ListView feedItemList;
    ArrayList<Story> stories = new ArrayList<>();
    String[] ids={"Vrushil","Rutvi","Meet","Hardi",
            "Vanshi","Priyank","Ariha","Chaitya",
            "Zinaaya","Dhyani","Smit","Navyaa",
            "Vairagi","Chaitali","Deep","Shilpa",
            "Prakash","Kirit","Rupal","Vikram",
            "Dharmesh","Urmila","Bharat","Vipul",
            "Kapila","Alka","Zeel","Shailesh"};
    int[] imgId={R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,
            R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8,
            R.drawable.i9,R.drawable.i10,R.drawable.i11,R.drawable.i12,
            R.drawable.i13,R.drawable.i14,R.drawable.i15,R.drawable.i16,
            R.drawable.i17,R.drawable.i18,R.drawable.i19,R.drawable.i20,
            R.drawable.i21,R.drawable.i22,R.drawable.i23,R.drawable.i24,
            R.drawable.i25,R.drawable.i26,R.drawable.i27,R.drawable.i28};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        storyBar=view.findViewById(R.id.storyList);
        Context context= view.getContext();
        for (int i=0;i<7;i++){
            Story story=new Story(false,ids[i],imgId[i]);
            stories.add(story);
        }
        StoryBarAdapter adapter=new StoryBarAdapter(stories, context);
        storyBar.setAdapter(adapter);
        storyBar.setClickable(false);
        storyBar.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        ListView feedList=view.findViewById(R.id.feedItemList);
        ArrayList<Story> feedData=new ArrayList<>();
        for(int i=0;i<3;i++){
            Story story=new Story(false,ids[i+5],imgId[i+5]);
            feedData.add(story);
        }
        FeedAdapter feedAdapter=new FeedAdapter(view.getContext(),feedData);
        feedList.setAdapter(feedAdapter);
        return view;
    }
}
