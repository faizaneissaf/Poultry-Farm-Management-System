package com.example.poultryfarmmanagementsystem.Customer;

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

public class Customer_BroilersSale extends AppCompatActivity {
    RecyclerView cb_rv;
    List<c_broilers_list_tbl> broilers_list;
    c_broilers_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_broilers_sale);
        cb_rv=findViewById(R.id.c_broilers_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        broilers_list =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Customer/broilerOnsale?pltryid="+customer_static_data.bbid;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                c_broilers_list_tbl data = new c_broilers_list_tbl();
                                data.setCb_bname(jsonObject.getString("b_name").toString());
                                data.setCb_bbid(jsonObject.getInt("bb_id"));
                                data.setCb_priceperchick(jsonObject.getInt("bb_priceperchick"));
                                data.setCb_totalqty(jsonObject.getInt("bb_totalqty"));
                                data.setCb_avgbodyweight(jsonObject.getInt("bb_avgbodyweight"));
                                data.setCb_pltryid(jsonObject.getInt("pltry_id"));
                                data.setCb_saleid(jsonObject.getInt("sale_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                broilers_list.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        cb_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new c_broilers_rv_adapter(getApplicationContext(), broilers_list);
                        cb_rv.setAdapter(adapter);
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