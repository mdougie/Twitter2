package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.restclienttemplate.models.ComposeActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    TwitterClient client; //TODO - make private?
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO error; Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApp.getRestClient();

        //find the RecyclerView
        rvTweets = (RecyclerView) findViewById(R.id.rvTweet);

        //init the arraylist (data source)
        tweets = new ArrayList<>();

        //construct the adapter from this data source
        tweetAdapter = new TweetAdapter(tweets);

        //RecyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(this));

        //set the adapter
        rvTweets.setAdapter(tweetAdapter);

        populateTimeline();
    }

    //TODO check for error
    private final int REQUEST_CODE = 20;
    public void onComposeAction(MenuItem mi) {
        // handle click here
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        //TODO check here
        startActivityForResult(i, REQUEST_CODE); // brings up the second activity
    }

    //TO DO PART TO TAKE OUT
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
            tweets.add(0, tweet);
            tweetAdapter.notifyItemInserted(0);
            rvTweets.scrollToPosition(0);
        }
    }

    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                Log.d("TwitterClient", response.toString());
                //iterate thru JSONArray
                //deserialize JSONObject for each obj
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
