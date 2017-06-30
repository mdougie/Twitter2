package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetail extends AppCompatActivity {

    //tweet setup
    Tweet tweet;
    TextView tvTweetDetail;
    Button btReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        //create view objects
        tvTweetDetail = (TextView) findViewById(R.id.tvTweetDetail);
        btReply = (Button) findViewById(R.id.btReply);
        //unwrap movie passed in through intent with simple name
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        Log.d("TweetDetail", String.format("Showing details for '%s'", tweet.body.toString()));
        //set view
        tvTweetDetail.setText(tweet.body);
        btReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeReply(v);
                Log.i("ENTERED", "onClick: ENTEREd ONCLICK");
            }

            //TODO - is error here
            int REQUEST_CODE = 20;
            public void composeReply(View v) {
                //TODO - do intent
                Intent i = new Intent(TweetDetail.this, ReplyActivity.class);
                i.putExtra("tweet", Parcels.wrap(tweet));
                startActivityForResult(i, REQUEST_CODE); // brings up the second activity
            }
        });

    //TODO - FIND A DIFFERENT WAY TO SEND INFO OR DO SOMETHING TO HAVE IT GO BACK TO TIMELINE INSTEAD OF DETAIL
    }
}
