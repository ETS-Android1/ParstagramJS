package com.example.parstagramjs.fragments;

import android.util.Log;

import com.example.parstagramjs.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostFragment {

    public static final String TAG = "ProfileFragment";

    @Override
    protected void queryPosts() {
        Log.i(TAG, "Inside queryPosts()");
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        Log.i(TAG, "After getting query");
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if ( e != null ) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                // Retrieving posts was successful
                for ( Post post : posts ) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                adapter.clear();
                adapter.addAll(posts);
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
