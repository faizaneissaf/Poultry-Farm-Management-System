package com.example.poultryfarmmanagementsystem.Owner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.User_Information;

import org.json.JSONException;
import org.json.JSONObject;

public class Owner_Addworker extends AppCompatActivity {
    EditText wname,wemail,wphone,waddress,wpassword;
    Button addworker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_addworker);
        wname=findViewById(R.id.wname);
        wemail=findViewById(R.id.wemail);
        wphone=findViewById(R.id.wphone);
        waddress=findViewById(R.id.waddress);
        wpassword=findViewById(R.id.wpassword);
        addworker=findViewById(R.id.btn_addworkerp);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//        Signup btn
        addworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("user_id", User_Information.user_id);
                    obj.put("worker_name",wname.getText().toString());
                    obj.put("worker_email",wemail.getText().toString());
                    obj.put("worker_password",wpassword.getText().toString());
                    obj.put("worker_address",waddress.getText().toString());
                    obj.put("worker_phoneno",wphone.getText().toString());
                    obj.put("pltry_id",Poultry_static_data.pltry_id);
                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/addWorker";
//                    Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Email Already Registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    requestQueue.add(jsonObjectRequest);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //-----
    }
}