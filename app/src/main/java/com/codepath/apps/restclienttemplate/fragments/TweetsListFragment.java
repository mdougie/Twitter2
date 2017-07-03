package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by madelynd on 7/2/17.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener {

    public interface TweetSelectedListener{
        //handle tweet selection
        public void onTweetSelected(Tweet tweet);
    }
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;
//    SwipeRefreshLayout swipeContainer;

    //inflation happens inside onCreateView

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

//        swipeContainer = (SwipeRefreshLayout) v.findViewById(swipeContainer);
//        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
//            }
//        });
//        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

        //find the RecyclerView
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTweets.addItemDecoration(itemDecoration);

        //init the arraylist (data source)
        tweets = new ArrayList<>();

        //construct the adapter from this data source
        tweetAdapter = new TweetAdapter(tweets, this);

        //RecyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));

        //set the adapter
        rvTweets.setAdapter(tweetAdapter);
        return v;
    }

//    public void fetchTimelineAsync(int page) {
//        // Send the network request to fetch the updated data
//        // `client` here is an instance of Android Async HTTP
//        // getHomeTimeline is an example endpoint.
//
//        TwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
//            /*
//            public void onSuccess(JSONArray json) {
//                // Remember to CLEAR OUT old items before appending in the new ones
//                tweetAdapter.clear();
//                // ...the data has come back, add new items to your adapter...
//                tweetAdapter.addAll(tweets);
//                // Now we call setRefreshing(false) to signal refresh has finished
//                swipeContainer.setRefreshing(false);
//            }
//            */
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                tweetAdapter.clear();
//                //TODO check error here, maybe it clears all and we don't want it or it doesn't add properly
//                Log.i("DEBUG", "Entered onSuccess");
//                //add all
//                /*
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
//                */
//                //showProgressBar();
//                populateTimeline();
//                swipeContainer.setRefreshing(false);
//                //delay action to show progress bar before it is hidden
//                /*
//                swipeContainer.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // change image
//                        hideProgressBar();
//                    }
//                }, 2000);
//                */
//            }
//
//            /*
//            public void onFailure(Throwable e) {
//                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
//            }
//            */
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                Log.d("DEBUG", "Fetch timeline error: " + throwable.toString());
//            }
//        });
//    }

    public void addItems(JSONArray response){
        for(int i=0; i<response.length(); i++) {
            //convert each object to tweet model
            //add tweet model to data source
            //notify adapter that we've added an item
            Tweet tweet = null;
            try {
                tweet = Tweet.fromJSON(response.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tweets.add(tweet);
            tweetAdapter.notifyItemInserted(tweets.size()-1);
        }
    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);
        //Toast.makeText(getContext(), tweet.body, Toast.LENGTH_SHORT).show();
        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }
}
