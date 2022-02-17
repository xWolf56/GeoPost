package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextLastName, textInputEditTextFirstName, textInputEditTextPassword, textInputEditTextEmail, textInputEditTextBirthday;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressBar = findViewById(R.id.progress);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        textInputEditTextFirstName = findViewById(R.id.first_name);
        textInputEditTextLastName = findViewById(R.id.last_name);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextBirthday = findViewById(R.id.birth_date);

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        findViewById(R.id.loginText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

    }

    private void registerUser() {
        final String firstName = textInputEditTextFirstName.getText().toString().trim();
        final String lastName = textInputEditTextLastName.getText().toString().trim();
        final String email = textInputEditTextEmail.getText().toString().trim();
        final String password = textInputEditTextPassword.getText().toString().trim();
        final String birthdate = textInputEditTextBirthday.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            textInputEditTextFirstName.setError("Please enter your first name");
            textInputEditTextFirstName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            textInputEditTextLastName.setError("Please enter your last name");
            textInputEditTextLastName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            textInputEditTextEmail.setError("Please enter your email");
            textInputEditTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEditTextEmail.setError("Enter a valid email");
            textInputEditTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(birthdate)) {
            textInputEditTextPassword.setError("Enter your birthdate");
            textInputEditTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            textInputEditTextPassword.setError("Enter a password");
            textInputEditTextPassword.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, String.format(URLs.URL_REGISTER, lastName, firstName, email, password, birthdate),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                User user = new User(
                                        obj.getString("firstName"),
                                        obj.getString("lastName"),
                                        obj.getString("email"),
                                        obj.getString("birthDate")
                                );

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}