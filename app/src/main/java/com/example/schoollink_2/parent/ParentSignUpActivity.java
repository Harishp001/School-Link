package com.example.schoollink_2.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoollink_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParentSignUpActivity extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword, signup_phone, signup_address;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signup_phone = findViewById(R.id.signup_phone);
        signup_address = findViewById(R.id.signup_address);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("parents");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String phone = signup_phone.getText().toString();
                String address = signup_address.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                ParentHelperClass parentHelperClass = new ParentHelperClass(name, email, phone, address, username, password);
                reference.child(username).setValue(parentHelperClass);

                Toast.makeText(ParentSignUpActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ParentSignUpActivity.this, ParentLoginActivity.class);
                startActivity(intent);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentSignUpActivity.this, ParentLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}