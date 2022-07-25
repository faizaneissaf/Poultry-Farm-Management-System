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

public class Admin_Poultries extends AppCompatActivity {
    RecyclerView admin_pltry_rv;
    List<poultries_list_tbl> poultries;
    poultries_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_poultries);
        admin_pltry_rv=findViewById(R.id.pltries_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //----------------
        //        Populating Recycler View
        poultries =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Admin/allPoultries";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                poultries_list_tbl data = new poultries_list_tbl();
                                data.setPltryname(jsonObject.getString("pltry_name").toString());
                                data.setPltryaddress(jsonObject.getString("pltry_address").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                poultries.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        admin_pltry_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new poultries_rv_adapter(getApplicationContext(), poultries);
                        admin_pltry_rv.setAdapter(adapter);
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