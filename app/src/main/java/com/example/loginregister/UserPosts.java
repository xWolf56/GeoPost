package com.example.loginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserPosts extends AppCompatActivity {

    // Create a variable for each posts : ImageView userImagesPosts[]
    // Create a variable here for the coordinate of each posts:
    // double coordLat[]
    // double coordLong[]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_geopost_logo);


        //  Here we pass through a loop to create an ImageView for every Post
        //  and add the coordinate of those post right below each posts.

        /*
        LinearLayout layout = (LinearLayout)findViewById(R.id.parentLayout);
        for(int i = 0; i < userImagePost.length; i++) {
            ImageView image = new ImageView(context);
            layout.addView(image);

            for(int j = 0; j < geoPostCoordinates.length; j++) {
                TextView postCoordinate = new TextView (context);
                layout.addView(postCoordinate)
                ....
                add coordLat and coordLong to the current TextView
            }

        }
        */
    }
}