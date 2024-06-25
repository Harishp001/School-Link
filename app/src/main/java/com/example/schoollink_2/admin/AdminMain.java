package com.example.schoollink_2.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schoollink_2.Home_Login;
import com.example.schoollink_2.MainActivity;
import com.example.schoollink_2.R;
import com.example.schoollink_2.bus.BusView;
import com.example.schoollink_2.driver.DriverView;
import com.example.schoollink_2.more.MoreMain;
import com.example.schoollink_2.more.MoreView;
import com.example.schoollink_2.parent.ParentView;
import com.example.schoollink_2.route.RouteView;
import com.example.schoollink_2.school.SchoolView;
import com.example.schoollink_2.student.StudentView;

public class AdminMain extends AppCompatActivity {

    ImageView student, parent, bus, driver, route, more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        student = findViewById(R.id.studentImage);
        parent = findViewById(R.id.parentImage);
        bus = findViewById(R.id.busImage);
        driver = findViewById(R.id.driverImage);
        route = findViewById(R.id.routeImage);
        more = findViewById(R.id.moreImage);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, StudentView.class);
                startActivity(intent);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, ParentView.class);
                startActivity(intent);
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, BusView.class);
                startActivity(intent);
            }
        });

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, RouteView.class);
                startActivity(intent);
            }
        });

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, DriverView.class);
                startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, MoreMain.class);
                startActivity(intent);
            }
        });
    }
}