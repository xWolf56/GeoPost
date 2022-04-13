package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    TextView fullName, email, birthdate, postDate, postLocation;
    ImageView profilePicture, latestPost;
    Button consultPost, logoutButton;

    //GPSLocation gpsLocation = new GPSLocation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //gpsLocation.requestGPSPermission();

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {

            fullName = findViewById(R.id.fullNameTextView);
            email = findViewById(R.id.emailTextView);
            birthdate = findViewById(R.id.textViewBirthday);

            profilePicture = findViewById(R.id.profileImageView);
            latestPost = findViewById(R.id.latestPostImageView);
            postDate = findViewById(R.id.postDateTextDate);
            postLocation = findViewById(R.id.latestPostLocationTextView);

            logoutButton = findViewById(R.id.logoutButton);
            consultPost = findViewById(R.id.consultButton);

            User user = SharedPrefManager.getInstance(this).getUser();

            // Examples of how to setText
            /*
                fullName.setText(user.getFirstName() + user.getLastName());
                email.setText(user.getEmail());
                latestPost.setImageDrawable(user.profile());
                birthdate.setText(user.getBirthday());
            */
            logoutButton.setOnClickListener(this::onClick);
        }
        else{
            Intent intent = new Intent(UserProfile.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClick(View view){
        if(view.equals(logoutButton)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}