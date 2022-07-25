package com.example.poultryfarmmanagementsystem.Owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Owner_workers extends AppCompatActivity {
    Button addworkers;
    RecyclerView rv_workerlist;
    List<owner_worker_tbl_list> workers;
    owner_worker_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_workers);
        addworkers=findViewById(R.id.btn_addworkers);
        rv_workerlist=findViewById(R.id.workers_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //----------------
        addworkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Owner_Addworker.class);
                startActivity(intent);
            }
        });
        //
        //        Populating Recycler View
        workers =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/workerInfo?pltryid="+ Poultry_static_data.pltry_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owner_worker_tbl_list data = new owner_worker_tbl_list();
                                data.setWorker_name(jsonObject.getString("worker_name").toString());
                                data.setWorker_email(jsonObject.getString("worker_email").toString());
                                data.setWorker_phone(jsonObject.getString("worker_phoneno").toString());
                                data.setWorker_address(jsonObject.getString("worker_address").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                workers.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        rv_workerlist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owner_worker_rv_adapter(getApplicationContext(), workers);
                        rv_workerlist.setAdapter(adapter);
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