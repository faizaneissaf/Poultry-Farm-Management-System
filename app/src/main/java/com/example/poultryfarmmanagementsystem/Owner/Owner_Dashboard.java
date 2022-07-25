package com.example.poultryfarmmanagementsystem.Owner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.User_Information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class Owner_Dashboard extends AppCompatActivity {
    Button btnaddp;
    EditText poultryname,poultryaddress;
    RecyclerView ownerpltries;
    List<owner_mypltry_tbl_list> pltries;
    owner_mypltry_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);
        btnaddp=findViewById(R.id.add_poultryBtn);
        poultryname=findViewById(R.id.et_pltryName);
        poultryaddress=findViewById(R.id.et_pltryadd);
        ownerpltries=findViewById(R.id.owner_pltry_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//----User Profile
        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/loginsignup/userInfo?email="+User_Information.useremail;
        Poultry_static_data.ipadd=getString(R.string.ip);
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
        //----Add poultry
        btnaddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.addpoultryfarm_form, null);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Owner_Dashboard.this);
                final EditText pname=(EditText) promptsView.findViewById(R.id.et_pltryName);
                final EditText padd=(EditText) promptsView.findViewById(R.id.et_pltryadd);
                alertDialog.setView(promptsView);
                        alertDialog.setTitle("Add New Poultry Farm")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String pn=pname.getText().toString();
                                String paddress=padd.getText().toString();
                                JSONObject obj=new JSONObject();
                                try {
                                    obj.put("user_id",User_Information.user_id);
                                    obj.put("pltry_name",pn);
                                    obj.put("pltry_address",paddress);
                                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/addPoultry";
                                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(getApplicationContext(), Owner_Dashboard.class);
                                            startActivity(intent);
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    );
                                    requestQueue.add(jsonObjectRequest);
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //-----------
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog.show();
                //--
            }
        });
        ///----------------------------------------
        //        Populating Recycler View
        pltries =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/myPoultries?oid="+User_Information.user_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owner_mypltry_tbl_list data = new owner_mypltry_tbl_list();
                                data.setPltry_Name(jsonObject.getString("pltry_name").toString());
                                data.setPltryid(jsonObject.getInt("pltry_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                pltries.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ownerpltries.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owner_mypltry_rv_adapter(getApplicationContext(), pltries);
                        ownerpltries.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);
//        Populating Recycler View
    }
}