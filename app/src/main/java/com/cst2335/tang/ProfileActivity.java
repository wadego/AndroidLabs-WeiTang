package com.cst2335.tang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.view.View;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton mImageButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.e(ACTIVITY_NAME, "In function: onCreate");

        // Get the Intent that started this activity
        Intent fromMain = getIntent();

        // Retrieve the email address from the Intent extras
        String emailAddress = fromMain.getStringExtra("EMAIL");

        // Find the email EditText in your layout (assuming its ID is "emailEditText")
        EditText emailEditText = findViewById(R.id.email_EditText);

        // Set the email address in the email EditText
        if (emailAddress != null) {
            emailEditText.setText(emailAddress);
        }

        mImageButton = findViewById(R.id.profile_button);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(ACTIVITY_NAME, "In function: onActivityResult");

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null){
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageButton.setImageBitmap(imageBitmap);
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}