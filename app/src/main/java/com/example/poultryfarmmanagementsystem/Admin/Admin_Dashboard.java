package com.example.poultryfarmmanagementsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.poultryfarmmanagementsystem.R;

public class Admin_Dashboard extends AppCompatActivity {
    LinearLayout btnowners,btncustomers,btnpoultries,btnworkers;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btnowners=findViewById(R.id.btn_owners);
        btncustomers=findViewById(R.id.btn_customers);
        btnworkers=findViewById(R.id.btn_workers);
        btnpoultries=findViewById(R.id.btn_poultries);
        logout=findViewById(R.id.adminlogout);
        btnowners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin_Owners.class);
                startActivity(intent);
            }
        });
        btncustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin_Customers.class);
                startActivity(intent);
            }
        });
        btnworkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin_Workers.class);
                startActivity(intent);
            }
        });
        btnpoultries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Admin_Poultries.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}