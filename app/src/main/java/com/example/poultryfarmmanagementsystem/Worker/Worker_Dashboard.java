package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.Owner.Owner_Dashboard;
import com.example.poultryfarmmanagementsystem.Owner.owner_mypltry_rv_adapter;
import com.example.poultryfarmmanagementsystem.Owner.owner_mypltry_tbl_list;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.User_Information;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Worker_Dashboard extends AppCompatActivity {
    Button addbatch,layerbbtn,broilerbbtn;
    TextView title;
    ImageView wlogout;
    RecyclerView batches_rv;
    List<worker_broiler_batches_tbl_list> broilerbatches;
    worker_broiler_batches_rv_adapter adapter;
    List<worker_layer_batches_tbl_list> layerbatches;
    worker_layer_batches_rv_adapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);
        addbatch=findViewById(R.id.btn_addbatch);
        title=findViewById(R.id.worker_title2);
        batches_rv=findViewById(R.id.worker_batches_rv);
        layerbbtn=findViewById(R.id.layer_batches_btn);
        broilerbbtn=findViewById(R.id.broilerbatchs_button);
        wlogout=findViewById(R.id.wlogoutimg2);
        wlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//        title.setText(User_Information.username);
        ///---
        //----User Profile
        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/loginsignup/workerInfo?email="+User_Information.useremail;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int n = 0; n < response.length(); n++)
                {
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Worker_StaticData.user_id= object.getInt("user_id");
                        Worker_StaticData.workerusername= object.getString("worker_name");
                        Worker_StaticData.workeraddress= object.getString("worker_address");
                        Worker_StaticData.workeremail= object.getString("worker_email");
                        Worker_StaticData.workerphoneno= object.getString("worker_phoneno");
                        Worker_StaticData.pltryid= object.getInt("pltry_id");
                        Worker_StaticData.worker_id=object.getInt("worker_id");
//                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT).show();
                        title.setText(Worker_StaticData.workerusername);
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
        ///------------------------------------------
        ////------
        addbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //--
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.batchchoicedialog, null);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Worker_Dashboard.this);
                final Button layer=(Button) promptsView.findViewById(R.id.button_layer);
                final Button broiler=(Button) promptsView.findViewById(R.id.button_broiler);
                alertDialog.setView(promptsView);
                broiler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Worker_AddnewBatch.class);
                        startActivity(intent);
                    }
                });
                layer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Worker_AddNewBatchLayer.class);
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
        ///--------------------
        //        Populating Recycler View
        broilerbatches =new ArrayList<>();
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/broilerBatches?wid="+Worker_StaticData.worker_id;
        JsonArrayRequest arrayRequest =new JsonArrayRequest(
                Request.Method.GET, url2, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                worker_broiler_batches_tbl_list data = new worker_broiler_batches_tbl_list();
                                data.setBroiler_batchname(jsonObject.getString("bb_name").toString());
                                data.setBroiler_batchid(jsonObject.getInt("bb_id"));
                                //-----------------------------------------------------------------------------------------------------------------
                                //-----------------------------------------------------------------------------------------------------------------
                                broilerbatches.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        batches_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new worker_broiler_batches_rv_adapter(getApplicationContext(), broilerbatches);
                        batches_rv.setAdapter(adapter);
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
        //--------------------
        layerbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                layerbatches =new ArrayList<>();
                String url3=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/layerBatches?wid="+Worker_StaticData.worker_id;
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url3, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        worker_layer_batches_tbl_list data = new worker_layer_batches_tbl_list();
                                        data.setLayer_batchname(jsonObject.getString("lb_name").toString());
                                        data.setLayerbatch_id(jsonObject.getInt("lb_id"));
                                        Layerbatch_StaticData.lb_totalc=jsonObject.getInt("lb_totalqty");
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        layerbatches.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                batches_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter2 = new worker_layer_batches_rv_adapter(getApplicationContext(), layerbatches);
                                batches_rv.setAdapter(adapter2);
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
        /////---------
        broilerbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Populating Recycler View
                broilerbatches =new ArrayList<>();
                String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/broilerBatches?wid="+Worker_StaticData.worker_id;
                JsonArrayRequest arrayRequest =new JsonArrayRequest(
                        Request.Method.GET, url2, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        worker_broiler_batches_tbl_list data = new worker_broiler_batches_tbl_list();
                                        data.setBroiler_batchname(jsonObject.getString("bb_name").toString());
                                        data.setBroiler_batchid(jsonObject.getInt("bb_id"));
                                        Broilerbatch_StaticData.bb_totalq=jsonObject.getInt("bb_totalqty");
                                        //-----------------------------------------------------------------------------------------------------------------
                                        //-----------------------------------------------------------------------------------------------------------------
                                        broilerbatches.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                batches_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter = new worker_broiler_batches_rv_adapter(getApplicationContext(), broilerbatches);
                                batches_rv.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Showing Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requestQueue.add(arrayRequest);
            }
        });
    }
}