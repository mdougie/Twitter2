package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class ReplyActivity extends AppCompatActivity {

    Tweet tweet;
    //Tweet firstTweet;
    //count for characters
    TextView tvCountR;
    EditText etTweetR;
    String reply;
    Button buttonReply;

    //display count for characters
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCountR.setText(String.valueOf(140-s.length())+" characters remaining");
            log.i("count", "entered"+String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.i("TESTING", "ENTERED ON CREATE IN REPLY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        tvCountR = (TextView) findViewById(R.id.tvCountR);
        etTweetR = (EditText) findViewById(R.id.etTweetR);
        etTweetR.addTextChangedListener(mTextEditorWatcher);
        buttonReply = (Button) findViewById(R.id.buttonReply);
        Intent intent = getIntent();
        //reply = Parcels.unwrap(intent.getParcelableExtra("reply"));
        //reply = firstTweet.user.screenName;
        //unwrap movie passed in through intent with simple name
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        reply = tweet.user.screenName;
        //Log.d("TweetDetail", String.format("Showing details for '%s'", tweet.body.toString()));
        //set view
        buttonReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replyTweet(v);
                Log.i("ENTERED", "onClick: ENTEREd ONCLICK");
            }
            public void replyTweet(final View view) {
                // Do something in response to button click
                log.i("CHECK", "ENTERED REPLY TWEET");
                TwitterClient twitterClient = new TwitterClient(ReplyActivity.this);
                //TODO - this is where you add the reply
                twitterClient.sendTweet(reply + " " + etTweetR.getText().toString(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //tweet = null;
                        log.i("check", "successREPLY");
                        try {
                            tweet = Tweet.fromJSON(response);
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

//            int REQUEST_CODE = 20;
//            public void composeReply(View v) {
//                //TODO - do intent
//                Intent i = new Intent(ReplyActivity.this, TimelineActivity.class);
//                startActivityForResult(i, REQUEST_CODE); // brings up the second activity
//            }
        });
//        buttonReply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replyTweet(v);
//            }
//        });

    }

    /** Called when the user touches the button */
    //TODO PART TO TAKE OUT




}
