package com.example.poultryfarmmanagementsystem.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class Cusotmer_Dashboard extends AppCompatActivity {
    CardView eggsbox,broilerbox;
    ImageView logout;
    RecyclerView csrv;

    List<customer_layerbatches_list_tbl> lp_list;
    customer_layerbatches_rv_adapter adapter;
    List<customer_broilerb_list_tbl> bb_list;
    customer_broilerb_rv_adapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusotmer_dashboard);
        eggsbox=findViewById(R.id.eggs_box);
        broilerbox=findViewById(R.id.broiler_box);
        logout=findViewById(R.id.logoutimg_c);
        csrv=findViewById(R.id.rv_pltriescustomer);

        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();


        //        Populating Recycler View
        lp_list =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Customer/bpoultries";
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                customer_layerbatches_list_tbl data = new customer_layerbatches_list_tbl();
                                data.setClbname(jsonObject.getString("pltry_name").toString());
                                data.setClbid(jsonObject.getInt("pltry_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                lp_list.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        csrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new customer_layerbatches_rv_adapter(getApplicationContext(), lp_list);
                        csrv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(arrayRequest);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        eggsbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lp_list =new ArrayList<>();
                String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Customer/bpoultries";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        customer_layerbatches_list_tbl data = new customer_layerbatches_list_tbl();
                                        data.setClbname(jsonObject.getString("pltry_name").toString());
                                        data.setClbid(jsonObject.getInt("pltry_id"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        lp_list.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                csrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new customer_layerbatches_rv_adapter(getApplicationContext(), lp_list);
                                csrv.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(arrayRequest);
//                Intent intent=new Intent(getApplicationContext(),Customer_EggsSale.class);
//                startActivity(intent);
            }
        });
        broilerbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bb_list =new ArrayList<>();
                String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Customer/bpoultries";
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        customer_broilerb_list_tbl data = new customer_broilerb_list_tbl();
                                        data.setCbbname(jsonObject.getString("pltry_name").toString());
                                        data.setCbbid(jsonObject.getInt("pltry_id"));
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        bb_list.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                csrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter2 = new customer_broilerb_rv_adapter(getApplicationContext(), bb_list);
                                csrv.setAdapter(adapter2);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(arrayRequest);
//                Intent intent=new Intent(getApplicationContext(),Customer_BroilersSale.class);
//                startActivity(intent);
            }
        });
    }
}