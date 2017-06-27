package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class ComposeActivity extends AppCompatActivity {

    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

    /** Called when the user touches the button */
    //TODO PART TO TAKE OUT
    private final int REQUEST_CODE = 20;
    public void composeTweet(View view) {
        // Do something in response to button click
        final EditText etTweet = (EditText) findViewById(R.id.etTweet);
        TwitterClient twitterClient = new TwitterClient(this);
        twitterClient.sendTweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //tweet = null;
                log.i("check", "success");
                try {
                    tweet = Tweet.fromJSON(response);
                    //tweet.body = etTweet.getText();
                    //TODO - ERROR HERE
                    Intent intent = new Intent();
                    intent.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                log.i("check", "failure" + errorResponse.toString());
            }
        });
    }



}
