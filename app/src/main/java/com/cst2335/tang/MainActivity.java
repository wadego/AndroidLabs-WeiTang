package com.cst2335.tang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Switch mySwitch;
    private boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        Button clickHere = findViewById(R.id.button);

        mySwitch = findViewById(R.id.switch1);
        switchState = mySwitch.isChecked();
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastMsg = getResources().getString(R.string.toast_message);
                Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_LONG).show();
            }
        });

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                switchState = isChecked;
                showSnackbar(isChecked);
            }
        });
    }

    private void showSnackbar(boolean isChecked) {
        String switchStatus = isChecked ? "on" : "off";
        String snackbarMsg = "The switch is now " + switchStatus;

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), snackbarMsg, Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySwitch.setChecked(!switchState);
            }
        });
        snackbar.show();
    }
}