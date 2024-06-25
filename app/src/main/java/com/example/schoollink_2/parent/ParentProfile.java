package com.example.schoollink_2.parent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;

public class ParentProfile extends AppCompatActivity {

    TextView profileName, profileEmail, profilePhone, profileAddress, profileUsername;
    TextView studentName, studentClass, studentDivision;
    Button logOut, homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profilePhone = findViewById(R.id.profile_mobile);
        profileAddress = findViewById(R.id.profile_address);
        profileUsername = findViewById(R.id.profile_username);

        studentName = findViewById(R.id.child_name);
        studentClass = findViewById(R.id.child_class);
        studentDivision = findViewById(R.id.child_division);

        logOut = findViewById(R.id.btn_logout);
        homeButton = findViewById(R.id.btn_return_home);

        SharedPreferences sharedPreferences = getSharedPreferences("ParentPrefs", MODE_PRIVATE);
        profileName.setText(sharedPreferences.getString("name", ""));
        profileEmail.setText(sharedPreferences.getString("email", ""));
        profilePhone.setText(sharedPreferences.getString("phone", ""));
        profileAddress.setText(sharedPreferences.getString("address", ""));
        profileUsername.setText(sharedPreferences.getString("username", ""));

        SharedPreferences selectedStudentPreferences = getSharedPreferences("SelectedStudent", MODE_PRIVATE);
        studentName.setText(selectedStudentPreferences.getString("studentName", ""));
        studentClass.setText(selectedStudentPreferences.getString("studentClass", ""));
        studentDivision.setText(selectedStudentPreferences.getString("division", ""));

        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(ParentProfile.this, ParentHome.class);
            startActivity(intent);
            finish();
        });

        logOut.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(ParentProfile.this, ParentLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
