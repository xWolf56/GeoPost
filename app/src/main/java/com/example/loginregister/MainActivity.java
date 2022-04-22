package com.example.loginregister;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    TextView firstName, lastName, userEmail, birthdate;
    Button btnLogout;
    GPSLocation gpsLocation = new GPSLocation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpsLocation.requestGPSPermission();

        Intent intent = new Intent(MainActivity.this, ActivityStream.class);
        startActivity(intent);
        finish();
/*
        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            firstName = findViewById(R.id.textViewFirstName);
            lastName = findViewById(R.id.textViewLastName);
            userEmail = findViewById(R.id.textViewEmail);
            birthdate = findViewById(R.id.textViewBirthday);
            btnLogout = findViewById(R.id.buttonLogout);
            User user = SharedPrefManager.getInstance(this).getUser();

            userEmail.setText(user.getEmail());
            lastName.setText(user.getLastName());
            firstName.setText(user.getFirstName());
            birthdate.setText(user.getBirthday());

            btnLogout.setOnClickListener(this::onClick);
        }
        else{
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == GPSLocation.MY_PERMISSIONS_FINE_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                gpsLocation.getLocation();

                Toast.makeText(this, String.valueOf(gpsLocation.getLatitude()), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, String.valueOf(gpsLocation.getLongitude()), Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}


