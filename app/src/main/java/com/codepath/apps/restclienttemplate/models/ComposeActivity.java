package com.codepath.apps.restclienttemplate.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    //count for characters
    TextView tvCount;
    EditText etTweet;
    //MenuItem miActionProgressItem;

    /*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
        // Extract the action-view from the menu item
        ProgressBar v =  (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
        // Return to finish
        return super.onPrepareOptionsMenu(menu);
    }

    public void showProgressBar() {
        // Show progress item
        miActionProgressItem.setVisible(true);
    }

    public void hideProgressBar() {
        // Hide progress item
        miActionProgressItem.setVisible(false);
    }
    */

    //display count for characters
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCount.setText(String.valueOf(140-s.length())+" characters remaining");
            log.i("count", "entered"+String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        tvCount = (TextView) findViewById(R.id.tvCount);
        etTweet = (EditText) findViewById(R.id.etTweetR);
        etTweet.addTextChangedListener(mTextEditorWatcher);
    }

    /** Called when the user touches the button */
    //TODO PART TO TAKE OUT
    private final int REQUEST_CODE = 20;
    //TODO take view not final
    public void composeTweet(final View view) {
        // Do something in response to button click
        //etTweet = (EditText) findViewById(R.id.etTweet);
        //count:
        TwitterClient twitterClient = new TwitterClient(this);
        twitterClient.sendTweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //tweet = null;
                log.i("check", "success");
                try {
                    //progress bar
                    //showProgressBar();
                    tweet = Tweet.fromJSON(response);
                    //tweet.body = etTweet.getText();
                    //TODO - ERROR HERE
                    Intent intent = new Intent();
                    intent.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK, intent);
                    //delay progress bar being hidden so it shows
                    /*
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // change image
                            hideProgressBar();
                        }
                    }, 2000);
                    */
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
