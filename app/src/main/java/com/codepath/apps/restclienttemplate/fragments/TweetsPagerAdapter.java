package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by madelynd on 7/2/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;
    private HomeTimelineFragment homeTimelineFragment;
    private MentionsTimelineFragment mentionsTimelineFragment;
    //int location;

    public TweetsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    //return the total number of fragments

    @Override
    public int getCount() {
        return 2;
    }

    //return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            homeTimelineFragment = getHomeTimeLineFragmentInstance();
            return homeTimelineFragment;
        }
        else if(position==1){
            mentionsTimelineFragment = getMentionsTimeLineFragmentInstance();
        return mentionsTimelineFragment;
        }
        else{
            return null;
        }
//        switch (position){
//        case 0:
//            homeTimelineFragment = getHomeTimeLineFragmentInstance();
//            return homeTimelineFragment;
//        case 1:
//            mentionsTimelineFragment = getMentionsTimeLineFragmentInstance();
//            return mentionsTimelineFragment;
//        default:
//            return null;
//        }
    }

//    switch (position){
//        case 0:
//            homeTimelineFragment = getHomeTimeLineFragment();
//            return homeTimelineFragment;
//        case 1:
//            mentionsTimelineFragment
//        default:
//            return null;
//    }
//
    private HomeTimelineFragment getHomeTimeLineFragmentInstance () {
        if(homeTimelineFragment == null){
            return new HomeTimelineFragment();
        }
        return homeTimelineFragment;
    }

    private MentionsTimelineFragment getMentionsTimeLineFragmentInstance () {
        if(mentionsTimelineFragment == null){
            return new MentionsTimelineFragment();
        }
        return mentionsTimelineFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // return title based on position
        return tabTitles[position];
    }
}
