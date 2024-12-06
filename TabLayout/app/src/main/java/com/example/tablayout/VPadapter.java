package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class VPadapter  extends FragmentPagerAdapter {


    public VPadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return  new FirstFragment();
        }
        else if(position==1){
            return  new SecondFragment();
        }
        else
            return new ThirdFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return  "CHATS";
        }
        else if(position==1){
            return  "STATUS";
        }
        else
            return "CALLS";
    }
}
