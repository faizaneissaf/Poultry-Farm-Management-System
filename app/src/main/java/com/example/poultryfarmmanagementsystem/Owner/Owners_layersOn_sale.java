package com.example.poultryfarmmanagementsystem.Owner;

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

public class Owners_layersOn_sale extends AppCompatActivity {
    RecyclerView lbonSale_rv;
    List<owner_layerson_sales_list_tbl> los_list;
    owner_layerson_sales_list_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_layers_on_sale);
        lbonSale_rv=findViewById(R.id.lb_sales_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        los_list =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/lbputOnsale?pltryid="+ Poultry_static_data.pltry_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owner_layerson_sales_list_tbl data = new owner_layerson_sales_list_tbl();
                                data.setLos_bname(jsonObject.getString("lb_name").toString());
                                data.setLos_lbid(jsonObject.getInt("lb_id"));
                                int tq=jsonObject.getInt("lb_totalqty");
                                int dc=jsonObject.getInt("mortality");
                                data.setLos_totalqty(tq-dc);
                                data.setLos_ageindays(jsonObject.getInt("ageindays"));
                                data.setLos_avgbodyweight(jsonObject.getInt("eggs_produced"));
                                data.setLos_pltryid(jsonObject.getInt("pltry_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                los_list.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lbonSale_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owner_layerson_sales_list_rv_adapter(getApplicationContext(), los_list);
                        lbonSale_rv.setAdapter(adapter);
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