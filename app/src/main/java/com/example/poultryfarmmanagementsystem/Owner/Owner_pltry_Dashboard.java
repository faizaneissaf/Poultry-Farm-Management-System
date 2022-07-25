package com.example.poultryfarmmanagementsystem.Owner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.LoginPage;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.User_Information;
import com.example.poultryfarmmanagementsystem.Worker.Worker_AddNewBatchLayer;
import com.example.poultryfarmmanagementsystem.Worker.Worker_AddnewBatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Owner_pltry_Dashboard extends AppCompatActivity {
    TextView title;
    CardView workers,expenditurebx,salesbox,obox;
    ImageView logoutimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_pltry_dashboard);
        title=findViewById(R.id.textView12);
        workers=findViewById(R.id.workers_box);
        expenditurebx=findViewById(R.id.expenditure_box);
        salesbox=findViewById(R.id.sales_box);
        logoutimg=findViewById(R.id.logoutimg_owner);
        obox=findViewById(R.id.order_box);
        obox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Owner_salesPage.class);
                startActivity(intent);
            }
        });
        logoutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        expenditurebx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Owner_PltryExpenditure.class);
                startActivity(intent);
            }
        });
        salesbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //--
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.owner_batchsale_selection, null);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Owner_pltry_Dashboard.this);
                final Button layer=(Button) promptsView.findViewById(R.id.os_layer);
                final Button broiler=(Button) promptsView.findViewById(R.id.os_broiler);
                alertDialog.setView(promptsView);
                layer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Owners_layersOn_sale.class);
                        startActivity(intent);
                    }
                });
                broiler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Owner_broilersOn_sale.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog.show();
                //--
            }
        });
        //----poultry Profile
        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Owner/poultryInfo?pid="+ Poultry_static_data.pltry_id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int n = 0; n < response.length(); n++)
                {
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Poultry_static_data.pltry_Name= object.getString("pltry_name");
                        title.setText(Poultry_static_data.pltry_Name);
//                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        //-----
        workers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Owner_workers.class);
                startActivity(intent);
            }
        });
    }
}