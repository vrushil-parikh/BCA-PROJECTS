package com.example.instagram;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ResourceBundle;

public class StoryBarAdapter extends RecyclerView.Adapter<StoryBarAdapter.StoryBarViewHolder> {
    private List<Story> stories;
    private Context context;
    public StoryBarAdapter(List<Story> stories,Context context) {
        this.stories = stories;
        this.context=context;
    }

    @NonNull
    @Override
    public StoryBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.story_item,parent,false);
        StoryBarViewHolder viewHolder=new StoryBarViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryBarViewHolder holder, int position) {
        Story story=stories.get(position);
        holder.profile.setImageResource(story.getImgId());
        holder.id.setText(story.getId());
        //changing the background color if the story is seen
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "can't load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoryBarViewHolder extends RecyclerView.ViewHolder{
        ImageView profile;
        TextView id;
        CardView cardView;
        public StoryBarViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.storyItem);
            profile=itemView.findViewById(R.id.storyimg);
            id=itemView.findViewById(R.id.userid);
        }
    }
}
