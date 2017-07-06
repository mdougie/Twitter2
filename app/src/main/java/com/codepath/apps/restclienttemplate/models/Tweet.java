package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by madelynd on 6/26/17.
 */
@Parcel
public class Tweet {
    //list attributes
    public String body;
    public long uid; //database ID for tweet
    public User user;
    public String createdAt;
    public boolean favorited;

    //deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.favorited = jsonObject.getBoolean("favorited");
        return tweet;
    }
}
