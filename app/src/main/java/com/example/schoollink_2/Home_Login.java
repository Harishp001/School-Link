package com.example.schoollink_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.admin.AdminLoginActivity;
import com.example.schoollink_2.driver.DriverLoginActivity;
import com.example.schoollink_2.parent.ParentLoginActivity;

public class Home_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);

        Button btnAdmin = findViewById(R.id.btn_admin);
        Button btnDriver = findViewById(R.id.btn_driver);
        Button btnParent = findViewById(R.id.btn_parent);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AdminLoginActivity
                startActivity(new Intent(Home_Login.this, AdminLoginActivity.class));
            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DriverLoginActivity
                startActivity(new Intent(Home_Login.this, DriverLoginActivity.class));
            }
        });

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ParentLoginActivity
                startActivity(new Intent(Home_Login.this, ParentLoginActivity.class));
            }
        });
    }
}
