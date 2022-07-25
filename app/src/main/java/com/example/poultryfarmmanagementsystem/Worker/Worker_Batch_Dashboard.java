package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class Worker_Batch_Dashboard extends AppCompatActivity {
    TextView vaccinationbtn,currentstatusbtn,recordbtn,bbtitle;
    ImageView wbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_batch_dashboard);
        vaccinationbtn=findViewById(R.id.vaccination_btn);
        currentstatusbtn=findViewById(R.id.current_statusbtn);
        recordbtn=findViewById(R.id.record_btn);
        bbtitle=findViewById(R.id.txtbb_title);
        wbn=findViewById(R.id.wbbnotification);
        wbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Worker_Batch_Dashboard.this);
                String title1="--Vaccination--";
//                SpannableString ss = new SpannableString(title1);
//                StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
//                ss.setSpan(boldSpan, 0, title1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                String title2="--Feeding--";
                alertDialog.setMessage(title1+"\n"+Broilerbatch_StaticData.bnmsg+"\n"+title2+"\n"+Broilerbatch_StaticData.feedmsg);
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog.show();
            }
        });
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//----Batch Data
        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/worker/broilerbatchInfo?bbid="+ Broilerbatch_StaticData.bb_id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int n=0;n<response.length();n++){
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Broilerbatch_StaticData.bb_name= object.getString("bb_name");
                        Broilerbatch_StaticData.bb_arrivaldate= object.getString("bb_arrivaldate");
                        Broilerbatch_StaticData.bb_totalqty= object.getInt("bb_totalqty");
                        Broilerbatch_StaticData.bb_totalcst= object.getInt("bb_totalcost");
                        Broilerbatch_StaticData.bb_pltryid= object.getInt("pltry_id");
                        Broilerbatch_StaticData.bb_workerid= object.getInt("worker_id");
                        bbtitle.setText(Broilerbatch_StaticData.bb_name);

                        ///---------Calculating Age

                        ///----------Calculating Age

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
//------------------------
        //----------Batch Data Information
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/worker/broilerBatchDataInfo?bbid="+ Broilerbatch_StaticData.bb_id;
        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(
                Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int n=0;n<response.length();n++){
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Broilerbatch_StaticData.bbd_id= object.getInt("bbd_id");
                        Broilerbatch_StaticData.bb_feedtype= object.getString("feed_type");
                        Broilerbatch_StaticData.bb_cstoffeedinkg= object.getInt("costoffeedinkg");
                        Broilerbatch_StaticData.bb_vaccinename= object.getString("vaccine_name");
                        Broilerbatch_StaticData.bb_totalvaccincst= object.getInt("totalvaccine_cost");
                        Broilerbatch_StaticData.bb_totalfeedconsumedinkg= object.getInt("totalfeedconsumedinkg");
                        Broilerbatch_StaticData.bb_mortality= object.getInt("mortality");
                        Broilerbatch_StaticData.bb_tage= object.getInt("ageindays");
                        int x=object.getInt("ageindays");
                        if (x==0){
                            Broilerbatch_StaticData.bnmsg="Today IB classic | Spray\nand Gumboro | Sub cut Injection Needed";
                        }else if (x==5){
                            Broilerbatch_StaticData.bnmsg="Today ND | Spray\nand ND+H9 | Sub cut Injection Needed";
                        }else if (x==18){
                            Broilerbatch_StaticData.bnmsg="Today ND | Spray Needed";
                        }else{
                            Broilerbatch_StaticData.bnmsg="No Vaccination Needed";
                        }


                        ///---------Calculating Age
                        if (x==0 || x<8){
                            Broilerbatch_StaticData.feedmsg="Crumble";
                        }else if (x==9 || x<18){
                            Broilerbatch_StaticData.feedmsg="Crumble / Pellet";
                        }else if (x== 19 || x>29){
                            Broilerbatch_StaticData.feedmsg="Pellet";
                        }
                        ///----------Calculating Age

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest2);
//---------------------------------------------
        recordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_broilerbatchRecord.class);
                startActivity(intent);
            }
        });
        vaccinationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_BroilerbatchVaccination.class);
                startActivity(intent);
            }
        });
        currentstatusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_broilerbatchcurrentstatus.class);
                startActivity(intent);
            }
        });
    }
}