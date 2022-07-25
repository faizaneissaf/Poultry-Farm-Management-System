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
import com.example.poultryfarmmanagementsystem.Worker.Worker_StaticData;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_rv_adapter;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_tbl_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Owner_broilersOn_sale extends AppCompatActivity {
    RecyclerView bb_salesrv;
    List<owner_broilerson_sales_list_tbl> bos_list;
    owner_broilerson_sales_list_rv_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_broilers_on_sale);
        bb_salesrv=findViewById(R.id.bb_sales_rv);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //        Populating Recycler View
        bos_list =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/bbputOnsale?pltryid="+ Poultry_static_data.pltry_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                owner_broilerson_sales_list_tbl data = new owner_broilerson_sales_list_tbl();
                                data.setBos_bname(jsonObject.getString("bb_name").toString());
                                data.setBos_bbid(jsonObject.getInt("bb_id"));
                                int tq=jsonObject.getInt("bb_totalqty");
                                int dc=jsonObject.getInt("mortality");
                                data.setBos_totalqty(tq-dc);
                                data.setBos_ageindays(jsonObject.getInt("ageindays"));
                                data.setBos_avgbodyweight(jsonObject.getInt("avgbodyweightingrams"));
                                data.setBos_pltryid(jsonObject.getInt("pltry_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                bos_list.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        bb_salesrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new owner_broilerson_sales_list_rv_adapter(getApplicationContext(), bos_list);
                        bb_salesrv.setAdapter(adapter);
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