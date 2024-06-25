package com.example.schoollink_2.more;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.schoollink_2.R;
import com.example.schoollink_2.bus.AddManageBus;
import com.example.schoollink_2.bus.DeleteManageBus;
import com.example.schoollink_2.bus.UpdateBus;
import com.example.schoollink_2.driver.AddManageDriver;
import com.example.schoollink_2.driver.DeleteManageDriver;
import com.example.schoollink_2.driver.UpdateDriver;
import com.example.schoollink_2.route.AddManageRoute;
import com.example.schoollink_2.route.DeleteManageRoute;
import com.example.schoollink_2.route.UpdateRoute;
import com.example.schoollink_2.student.AddManageStudent;
import com.example.schoollink_2.student.DeleteManageStudent;
import com.example.schoollink_2.student.UpdateStudent;

public class MoreMain extends AppCompatActivity {

    CardView manageStudentCard, manageBusCard, manageDriverCard, manageRouteCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_main);

        manageStudentCard = findViewById(R.id.manageStudentCard);
        manageBusCard = findViewById(R.id.manageBusCard);
        manageDriverCard = findViewById(R.id.manageDriverCard);
        manageRouteCard = findViewById(R.id.manageRouteCard);

        manageStudentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageStudentDialog();
            }
        });

        manageBusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageBusDialog();
            }
        });

        manageDriverCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageDriverDialog();
            }
        });

        manageRouteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageRouteDialog();
            }
        });
    }

    private void showManageStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_manage_student, null);
        builder.setView(dialogView);

        Button addButton = dialogView.findViewById(R.id.addButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);
        Button updateButton = dialogView.findViewById(R.id.updateButton);

        final AlertDialog dialog = builder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to AddStudent activity
                Intent intent = new Intent(MoreMain.this, AddManageStudent.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, DeleteManageStudent.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, UpdateStudent.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showManageBusDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_manage_bus, null);
        builder.setView(dialogView);

        Button addButton = dialogView.findViewById(R.id.addButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);
        Button updateButton = dialogView.findViewById(R.id.updateButton);

        final AlertDialog dialog = builder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to AddStudent activity
                Intent intent = new Intent(MoreMain.this, AddManageBus.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, DeleteManageBus.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, UpdateBus.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showManageRouteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_manage_route, null);
        builder.setView(dialogView);

        Button addButton = dialogView.findViewById(R.id.addButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);
        Button updateButton = dialogView.findViewById(R.id.updateButton);

        final AlertDialog dialog = builder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to AddStudent activity
                Intent intent = new Intent(MoreMain.this, AddManageRoute.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, DeleteManageRoute.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, UpdateRoute.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showManageDriverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_manage_driver, null);
        builder.setView(dialogView);

        Button addButton = dialogView.findViewById(R.id.addButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);
        Button updateButton = dialogView.findViewById(R.id.updateButton);

        final AlertDialog dialog = builder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to AddStudent activity
                Intent intent = new Intent(MoreMain.this, AddManageDriver.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, DeleteManageDriver.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to DeleteStudent activity
                Intent intent = new Intent(MoreMain.this, UpdateDriver.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
