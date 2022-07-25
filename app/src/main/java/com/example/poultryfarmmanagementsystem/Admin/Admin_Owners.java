package com.example.poultryfarmmanagementsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Admin_Owners extends AppCompatActivity {
    RecyclerView admin_owner_rv;
    List<owners_list_tbl> owners;
    owners_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_owners);
        admin_owner_rv=findViewById(R.id.admin_owners_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //----------------
        //        Populating Recycler View
        owners =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Admin/allOwners";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owners_list_tbl data = new owners_list_tbl();
                                data.setOwnername(jsonObject.getString("user_name").toString());
                                data.setOwneremail(jsonObject.getString("user_email").toString());
                                data.setOwnerphone(jsonObject.getString("user_phoneno").toString());
                                data.setOwneradd(jsonObject.getString("user_address").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                owners.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        admin_owner_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owners_rv_adapter(getApplicationContext(), owners);
                        admin_owner_rv.setAdapter(adapter);
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