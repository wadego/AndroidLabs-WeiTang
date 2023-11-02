package com.cst2335.tang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Intent;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText email_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Button login_btn = findViewById(R.id.login_button);
        email_edit = findViewById(R.id.email_EditText);

        String savedEmail = sharedPreferences.getString("user_email","");
        email_edit.setText(savedEmail);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the email address from the EditText
                String emailAddress = email_edit.getText().toString();

                // Create an Intent to go to ProfileActivity
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);

                // Pass the email address as an extra to the Intent
                goToProfile.putExtra("EMAIL", emailAddress);

                // Start the ProfileActivity
                startActivity(goToProfile);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        String emailAddress = email_edit.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_email", emailAddress);
        editor.apply();
    }
}