package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends ArrayAdapter<Story> {

    public FeedAdapter(Context context,ArrayList<Story> feedData){


        super(context,R.layout.feed_item,feedData);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Story story=getItem(position);
        if (convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.feed_item,parent,false);
        }
        CircleImageView feedProfile=convertView.findViewById(R.id.feedProfile);
        ImageView feedPost=convertView.findViewById(R.id.feedPost);
        TextView userId=convertView.findViewById(R.id.feedUserId);
        TextView caption=convertView.findViewById(R.id.feedCaption);
        TextView comment=convertView.findViewById(R.id.feedcomment);
        View feedViewLike=convertView.findViewById(R.id.feedViewLike);
        View feedViewSave=convertView.findViewById(R.id.feedViewSave);
        feedProfile.setImageResource(story.getImgId());
        feedPost.setImageResource(story.getImgId());
        userId.setText(story.getId());
        caption.setText(getContext().getString(R.string.caption,story.getId()));
        comment.setText(getContext().getString(R.string.comment,"_vrushil._"));
        feedViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(story.like==0){
                    story.like=1;
                    feedViewLike.setBackground(getContext().getDrawable(R.drawable.ic_fill_like));
                }
                else{
                    story.like=0;
                    feedViewLike.setBackground(getContext().getDrawable(R.drawable.ic_like));
                }
            }
        });
        feedViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (story.save==0){
                    story.save=1;
                     feedViewSave.setBackground(getContext().getDrawable(R.drawable.ic_fill_save));
                }
                else {
                    story.save=0;
                    feedViewSave.setBackground(getContext().getDrawable(R.drawable.ic_save));
                }
            }
        });
        return convertView;
    }
}