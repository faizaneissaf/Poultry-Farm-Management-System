package com.example.poultryfarmmanagementsystem.Owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.Worker.Worker_StaticData;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_rv_adapter;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_tbl_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Owner_PltryExpenditure extends AppCompatActivity {
    Button btnlb,btnbb;
    RecyclerView exp_rv;
    List<owner_exp_list_tbl> explist;
    owner_exp_list_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_pltry_expenditure);
        exp_rv=findViewById(R.id.exp_rv);
        btnlb=findViewById(R.id.layerbtn_f);
        btnbb=findViewById(R.id.broilerbtn_f);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        explist =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/expenditureInfolb?pltryid="+ Poultry_static_data.pltry_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owner_exp_list_tbl data = new owner_exp_list_tbl();
                                data.setOe_batchname("Batch Name : "+jsonObject.getString("lb_name").toString());
                                data.setOe_totalqty("Total Batch Qty :"+jsonObject.getString("lb_totalqty").toString());
                                data.setOe_totalcst("Total Batch Cost :"+jsonObject.getString("lb_totalcost").toString());
                                data.setOe_totalfeedcst("Total Feed Cost :"+jsonObject.getString("costoffeedinkg").toString());
                                data.setOe_totalvaccinecst("Total Vaccine Cost :"+jsonObject.getString("totalvaccine_cost").toString());
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                explist.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        exp_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owner_exp_list_rv_adapter(getApplicationContext(), explist);
                        exp_rv.setAdapter(adapter);
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
        btnlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                explist =new ArrayList<>();
                String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/expenditureInfolb?pltryid="+ Poultry_static_data.pltry_id;
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        owner_exp_list_tbl data = new owner_exp_list_tbl();
                                        data.setOe_batchname("Batch Name : "+jsonObject.getString("lb_name").toString());
                                        data.setOe_totalqty("Total Batch Qty :"+jsonObject.getString("lb_totalqty").toString());
                                        data.setOe_totalcst("Total Batch Cost :"+jsonObject.getString("lb_totalcost").toString());
                                        data.setOe_totalfeedcst("Total Feed Cost :"+jsonObject.getString("costoffeedinkg").toString());
                                        data.setOe_totalvaccinecst("Total Vaccine Cost :"+jsonObject.getString("totalvaccine_cost").toString());
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        explist.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                exp_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new owner_exp_list_rv_adapter(getApplicationContext(), explist);
                                exp_rv.setAdapter(adapter);
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
        });
        btnbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                explist =new ArrayList<>();
                String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/expenditureInfobb?pltryid="+ Poultry_static_data.pltry_id;
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        owner_exp_list_tbl data = new owner_exp_list_tbl();
                                        data.setOe_batchname("Batch Name : "+jsonObject.getString("bb_name").toString());
                                        data.setOe_totalqty("Total Batch Qty :"+jsonObject.getString("bb_totalqty").toString());
                                        data.setOe_totalcst("Total Batch Cost :"+jsonObject.getString("bb_totalcost").toString());
                                        data.setOe_totalfeedcst("Total Feed Cost :"+jsonObject.getString("costoffeedinkg").toString());
                                        data.setOe_totalvaccinecst("Total Vaccine Cost :"+jsonObject.getString("totalvaccine_cost").toString());
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        explist.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                exp_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new owner_exp_list_rv_adapter(getApplicationContext(), explist);
                                exp_rv.setAdapter(adapter);
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
        });
    }
}