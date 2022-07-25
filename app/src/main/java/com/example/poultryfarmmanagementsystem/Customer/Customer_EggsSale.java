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
import com.example.poultryfarmmanagementsystem.Owner.Poultry_static_data;
import com.example.poultryfarmmanagementsystem.Owner.owner_layerson_sales_list_rv_adapter;
import com.example.poultryfarmmanagementsystem.Owner.owner_layerson_sales_list_tbl;
import com.example.poultryfarmmanagementsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Customer_EggsSale extends AppCompatActivity {
    RecyclerView eggs_rv;
    List<c_eggs_list_tbl> eggs_list;
    c_eggs_list_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_eggs_sale);
        eggs_rv=findViewById(R.id.c_eggs_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        eggs_list =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Customer/eggsOnsale?pltryid="+customer_static_data.lbid;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                c_eggs_list_tbl data = new c_eggs_list_tbl();
                                data.setCe_bname(jsonObject.getString("b_name").toString());
                                data.setCe_lbid(jsonObject.getInt("lb_id"));
                                data.setCe_pricepertray(jsonObject.getInt("lb_pricepertryofeggs"));
                                data.setCe_totaleggsintrays(jsonObject.getInt("lb_totaleggsintrays"));
                                data.setCe_pltryid(jsonObject.getInt("pltry_id"));
                                data.setCe_saleid(jsonObject.getInt("sale_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                eggs_list.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        eggs_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new c_eggs_list_rv_adapter(getApplicationContext(), eggs_list);
                        eggs_rv.setAdapter(adapter);
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