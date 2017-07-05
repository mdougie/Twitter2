package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.MentionsTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.ComposeActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TimelineActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener {

//    TwitterClient client; //TODO - make private?
    //SwipeRefreshLayout swipeContainer;
    TweetsPagerAdapter tweetsPagerAdapter;
    ViewPager vpPager;
    HomeTimelineFragment homeTimelineFragment;
    MentionsTimelineFragment mentionsTimelineFragment;

    //long lowestId;
    //MenuItem miActionProgressItem;

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // Store instance of the menu item containing progress
//        miActionProgressItem = menu.findItem(R.id.miActionProgress);
//        // Extract the action-view from the menu item
//        ProgressBar v =  (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
//        // Return to finish
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    public void showProgressBar() {
//        // Show progress item
//        miActionProgressItem.setVisible(true);
//    }
//
//    public void hideProgressBar() {
//        // Hide progress item
//        miActionProgressItem.setVisible(false);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO error; Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onProfileView(MenuItem item){
        //launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        //i.putExtra("activity", "MyProfile");
        startActivity(i);
    }

//    public void onTweetProfileView(MenuItem item){
//        //launch the profile view
//        Intent i = new Intent(this, TweetProfileActivity.class);
//        startActivity(i);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //get view pager
        vpPager = (ViewPager) findViewById(R.id.viewpager);
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager(), this);

        //set adapter for pager
        vpPager.setAdapter(tweetsPagerAdapter);

        //setup TabLayout to use vp
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);

//        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        //setSupportActionBar(myToolbar);
//        // Find the toolbar view inside the activity layout
//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        // Sets the Toolbar to act as the ActionBar for this Activity window.
//        // Make sure the toolbar exists in the activity and is not null
//        setSupportActionBar(toolbar);
//        toolbar.setTitleTextColor(0xFFFFFFFF);


//        client = TwitterApp.getRestClient();

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

//        populateTimeline();
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

    //TODO check for error
    private final int REQUEST_CODE = 20;
    public void onComposeAction(MenuItem mi) {
        // handle click here
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        //TODO check here
        startActivityForResult(i, REQUEST_CODE); // brings up the second activity
    }

    @Override
    public void onTweetSelected(Tweet tweet) {
        //launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
//        i.putExtra("name_screen", tweet.user.screenName);
//        i.putExtra("followers", tweet.user.followersCount);
//        i.putExtra("following", tweet.user.followingCount);
//        i.putExtra("tagLine", tweet.user.tagLine);
        i.putExtra("tweet", Parcels.wrap(tweet));
        startActivity(i);
    }

        // TODO ADD THESE IN MAYBE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check request code and result code first
        if (resultCode==RESULT_OK && requestCode==REQUEST_CODE){
            // Use data parameter
            //Intent i = new Intent();
            //TODO CHECK HERE - UNWRAP
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            //Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
            //add tweet
            homeTimelineFragment = (HomeTimelineFragment) tweetsPagerAdapter.getItem(0);
            vpPager.setCurrentItem(0);
            homeTimelineFragment.addTweet(tweet);
//            tweetAdapter.notifyItemInserted(0);
//            rvTweets.scrollToPosition(0);
//            ((HomeTimelineFragment) pagerAdapter.getItem(vPgr.getCurrentItem())).addTweet(tweet);
        }
    }

//    public void addTweet(Tweet tweet){
//        homeTimelineFragment.(0, tweet);
//        TweetsPagerAdapter.notifyItemInserted(0);
//        rvTweets.scrollToPosition(0);
//    }

//    private void populateTimeline() {
//        client.getHomeTimeline(new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d("TwitterClient", response.toString());
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
////                Log.d("TwitterClient", response.toString());
//                //iterate thru JSONArray
//                //deserialize JSONObject for each obj
////                for(int i=0; i<response.length(); i++) {
////                    //convert each object to tweet model
////                    //add tweet model to data source
////                    //notify adapter that we've added an item
////                    Tweet tweet = null;
////                    try {
////                        tweet = Tweet.fromJSON(response.getJSONObject(i));
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                    tweets.add(tweet);
////                    tweetAdapter.notifyItemInserted(tweets.size()-1);
////                }
//                fragmentTweetsList.addItems(response);
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.d("TwitterClient", responseString);
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//            }
//        });
//    }
}
