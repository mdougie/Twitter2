package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by madelynd on 7/2/17.
 */

public class UserTimelineFragment extends TweetsListFragment {
    TwitterClient client; //TODO - make private?

    public static UserTimelineFragment newInstance(String screenName){
        UserTimelineFragment userTimelineFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userTimelineFragment.setArguments(args);
        return userTimelineFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        populateTimeline();
    }

    private void populateTimeline() {
        //comes from the activity
        String screenName = getArguments().getString("screen_name");
        client.getUserTimeline(screenName, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                Log.d("TwitterClient", response.toString());
                //iterate thru JSONArray
                //deserialize JSONObject for each obj
//                for(int i=0; i<response.length(); i++) {
//                    //convert each object to tweet model
//                    //add tweet model to data source
//                    //notify adapter that we've added an item
//                    Tweet tweet = null;
//                    try {
//                        tweet = Tweet.fromJSON(response.getJSONObject(i));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    tweets.add(tweet);
//                    tweetAdapter.notifyItemInserted(tweets.size()-1);
//                }
                addItems(response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}