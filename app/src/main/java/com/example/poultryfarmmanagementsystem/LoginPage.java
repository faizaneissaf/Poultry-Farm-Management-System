package com.example.poultryfarmmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.Admin.Admin_Dashboard;
import com.example.poultryfarmmanagementsystem.Customer.Cusotmer_Dashboard;
import com.example.poultryfarmmanagementsystem.Customer.customer_static_data;
import com.example.poultryfarmmanagementsystem.Owner.Owner_Dashboard;
import com.example.poultryfarmmanagementsystem.Worker.Worker_Dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {
    EditText loginid,loginpass;
    Button btnlogin;
    TextView gotosignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loginid = findViewById(R.id.ettxt_loginid);
        loginpass=findViewById(R.id.ettxt_loginpassword);
        btnlogin=findViewById(R.id.login_btn_main);
        gotosignup=findViewById(R.id.txt_gotosignup);
//        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Getting ip from Strings
        String ip= getString(R.string.ip);
        customer_static_data.ipadd=ip;
//        Getting ip from Strings
//        Login Button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginid.getText().toString();
                String password=loginpass.getText().toString();
                String url=ip+"/PoultryFarmManagementSystem/api/loginsignup/userlogin?email="+email+"&password="+password;
//                Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
                StringRequest stringRequest=new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response + "", Toast.LENGTH_SHORT).show();
                        User_Information.useremail=email;
                        if (response.contains("Owner")){
                            Intent intent=new Intent(getApplicationContext(), Owner_Dashboard.class);
                            startActivity(intent);
                        }else if (response.contains("Worker")){
                            Intent intent=new Intent(getApplicationContext(), Worker_Dashboard.class);
                            startActivity(intent);
                        }
                        else if (response.contains("Admin")){
                            Intent intent=new Intent(getApplicationContext(), Admin_Dashboard.class);
                            startActivity(intent);
                        }
                        else if (response.contains("Customer")){
                            Intent intent=new Intent(getApplicationContext(), Cusotmer_Dashboard.class);
                            startActivity(intent);
                        }
                        ///-------------------------------------------
                        //----User Profile
                        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/loginsignup/userInfo?email="+User_Information.useremail;
                        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int n = 0; n < response.length(); n++)
                                {
                                    try {
                                        JSONObject object = response.getJSONObject(n);
//                                        int userid=object.getInt("user_id");
//                                        int workerid=object.getInt("worker_id");
//                                        if (userid!=null){
                                            User_Information.user_id= object.getInt("user_id");
                                            User_Information.username= object.getString("user_name");
                                            User_Information.useraddress= object.getString("user_address");
                                            User_Information.userphoneno= object.getString("user_phoneno");
                                            User_Information.useremail= object.getString("user_email");
                                            User_Information.usertype= object.getInt("user_type");
//                                        }
//                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(jsonArrayRequest);
                        ///------------------------------------------
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });

//        Signup
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SignupPage.class);
                startActivity(i);
            }
        });
    }
}