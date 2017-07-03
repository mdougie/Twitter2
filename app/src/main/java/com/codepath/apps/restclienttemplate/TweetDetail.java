package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetail extends AppCompatActivity {

    //tweet setup
    Tweet tweet;
    TextView tvTweetDetail;
    Button btReply;
    TextView tvUsername;
    ImageView ivProfile;
    ImageButton btFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        //create view objects
        tvTweetDetail = (TextView) findViewById(R.id.tvTweetDetail);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        btReply = (Button) findViewById(R.id.btReply);
        btFavorite = (ImageButton) findViewById(R.id.btFavorite);
        //unwrap movie passed in through intent with simple name
        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        Log.d("TweetDetail", String.format("Showing details for '%s'", tweet.body.toString()));
        //set view
        tvTweetDetail.setText(tweet.body);
        tvUsername.setText(tweet.user.screenName);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfile);
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
                finish();
            }
        });
        //TODO WHERE I AM CHANGING/ERROR
//        btFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                favorite(v);
//                Log.i("ENTERED", "onClick: ENTEREd ONCLICK");
//            }
//
//            //TODO - is error here
//            int REQUEST_CODE = 20;
//            public void favorite(View v) {
//                //TODO - do intent
//                Intent i = new Intent(TweetDetail.this, FavoriteActivity.class);
//                i.putExtra("tweet", Parcels.wrap(tweet));
//                startActivityForResult(i, REQUEST_CODE); // brings up the second activity
//                finish();
//            }
//        });

    //TODO - FIND A DIFFERENT WAY TO SEND INFO OR DO SOMETHING TO HAVE IT GO BACK TO TIMELINE INSTEAD OF DETAIL
    }
}
