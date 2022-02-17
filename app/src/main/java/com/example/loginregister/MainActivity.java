package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView firstName, lastName, userEmail, birthdate;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
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
        }
    }

    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
