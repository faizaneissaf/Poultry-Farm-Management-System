package com.example.poultryfarmmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText email,name,password;
    Button createacc;
    Spinner sp_role;
    TextView gotologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        email=findViewById(R.id.et_suEmail);
        name=findViewById(R.id.et_suName);
        password=findViewById(R.id.et_suPassword);
        createacc=findViewById(R.id.btn_createacc);
        gotologin=findViewById(R.id.txt_gotologin);
        sp_role=findViewById(R.id.spinner_role);
//        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//        Signup btn
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("user_name",name.getText().toString());
                    obj.put("user_email",email.getText().toString());
                    obj.put("user_password",password.getText().toString());
                    obj.put("user_type",sp_role.getSelectedItemId());
                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/loginsignup/Signup";
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
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
//        go to login page
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(i);
            }
        });
        //        Spinner
        ArrayAdapter<CharSequence> spadapter=ArrayAdapter.createFromResource(this,R.array.acc_type,R.layout.support_simple_spinner_dropdown_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_role.setAdapter(spadapter);
        sp_role.setOnItemSelectedListener(this);
    }
//  Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}