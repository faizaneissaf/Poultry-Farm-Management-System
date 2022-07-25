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

public class Admin_Workers extends AppCompatActivity {
    RecyclerView worker_rvadmin;
    List<workers_list_tbl> workers2;
    worker_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_workers);
        worker_rvadmin=findViewById(R.id.workers_rv_admin);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //----------------
        //        Populating Recycler View
        workers2 =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Admin/allWorkers";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                workers_list_tbl data = new workers_list_tbl();
                                data.setWorkername(jsonObject.getString("worker_name").toString());
                                data.setWorkeremail(jsonObject.getString("worker_email").toString());
                                data.setWorkerphone(jsonObject.getString("worker_phoneno").toString());
                                data.setWorkeradd(jsonObject.getString("worker_address").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                workers2.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        worker_rvadmin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new worker_rv_adapter(getApplicationContext(), workers2);
                        worker_rvadmin.setAdapter(adapter);
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