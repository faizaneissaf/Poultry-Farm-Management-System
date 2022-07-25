package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Worker_Layerbatch_dashboard extends AppCompatActivity {
    TextView lcurrentstatusbtn,lrecrodbtn,lvaccinationbtn,lbtitle;
    ImageView lnbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_layerbatch_dashboard);
        lcurrentstatusbtn=findViewById(R.id.layercurrent_statusbtn);
        lrecrodbtn=findViewById(R.id.layerrecord_btn);
        lvaccinationbtn=findViewById(R.id.layervaccination_btn);
        lbtitle=findViewById(R.id.lb_titletxt);
        lnbtn=findViewById(R.id.lnbtn);

        lnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Worker_Layerbatch_dashboard.this);
                String title1="--Vaccination--";
//                SpannableString ss = new SpannableString(title1);
//                StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
//                ss.setSpan(boldSpan, 0, title1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                String title2="--Feeding--";
                alertDialog.setMessage(title1+"\n"+Layerbatch_StaticData.vmsg+"\n"+title2+"\n"+Layerbatch_StaticData.feedingmsg);
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
        //------------------------------------------------------
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
//----Batch Data
        String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/worker/layerBatchInfo?lbid="+ Layerbatch_StaticData.lb_id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int n=0;n<response.length();n++){
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Layerbatch_StaticData.lb_name= object.getString("lb_name");
                        Layerbatch_StaticData.lb_arrivaldate= object.getString("lb_arrivaldate");
                        Layerbatch_StaticData.lb_totalqty= object.getInt("lb_totalqty");
                        Layerbatch_StaticData.lb_totalcst= object.getInt("lb_totalcost");
                        Layerbatch_StaticData.lb_pltryid= object.getInt("pltry_id");
                        Layerbatch_StaticData.lb_workerid= object.getInt("worker_id");
                        lbtitle.setText(Layerbatch_StaticData.lb_name);

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
        ///---------------Layer data info
        //----------Batch Data Information
        String url2=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/worker/layerBatchDataInfo?lbid="+ Layerbatch_StaticData.lb_id;
        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(
                Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int n=0;n<response.length();n++){
                    try {
                        JSONObject object = response.getJSONObject(n);
                        Layerbatch_StaticData.lbd_id= object.getInt("lbd_id");
                        Layerbatch_StaticData.lb_feedtype= object.getString("feed_type");
                        Layerbatch_StaticData.lb_cstoffeedinkg= object.getInt("costoffeedinkg");
                        Layerbatch_StaticData.lb_vaccinename= object.getString("vaccine_name");
                        Layerbatch_StaticData.lb_totalvaccincst= object.getInt("totalvaccine_cost");
                        Layerbatch_StaticData.lb_totalfeedconsumedinkg= object.getInt("totalfeedconsumedinkg");
                        Layerbatch_StaticData.lb_mortality= object.getInt("mortality");
                        Layerbatch_StaticData.lb_tage= object.getInt("ageindays");
                        Layerbatch_StaticData.lb_totaleggproduced= object.getInt("eggs_produced");
                        int x=object.getInt("ageindays");
                        if (x==1){
                            Layerbatch_StaticData.vmsg="IBH-120, IB variant";
                        }else if (x==5){
                            Layerbatch_StaticData.vmsg="ND+H9 killed (ND Lasota Live)";
                        }else if (x==9){
                            Layerbatch_StaticData.vmsg="IBD (intermediate) + IBD killed";
                        }
                        else if (x==16){
                            Layerbatch_StaticData.vmsg="IBD (plus)";
                        }
                        else if (x==20){
                            Layerbatch_StaticData.vmsg="H5";
                        }
                        else if (x==23){
                            Layerbatch_StaticData.vmsg="ND Lasota";
                        }
                        else if (x==28){
                            Layerbatch_StaticData.vmsg="IBH120";
                        }
                        else if (x==35){
                            Layerbatch_StaticData.vmsg="Fowl pox";
                        }
                        else if (x==50){
                            Layerbatch_StaticData.vmsg="H5";
                        }else if (x==55){
                            Layerbatch_StaticData.vmsg="ND+H9 Killed (ND Lasota live)";
                        }
                        else if (x==70){
                            Layerbatch_StaticData.vmsg="IB variant";
                        }
                        else if (x==9){
                            Layerbatch_StaticData.vmsg="ND+IB+EDS";
                        }
                        else if (x==9){
                            Layerbatch_StaticData.vmsg="ND+IB live";
                        }

                        else{
                            Layerbatch_StaticData.vmsg="No Vaccination Needed";
                        }


                        ///---------Calculating Age
                        if (x==0 || x<13){
                            Layerbatch_StaticData.feedingmsg="25% Chick mash & 75% Chick crumble";
                        }else if (x==14){
                            Layerbatch_StaticData.feedingmsg="50% Chick mash & 50% Chick crumble";
                        }else if (x==15){
                            Layerbatch_StaticData.feedingmsg="75% Chick mash & 25% Chick crumble";
                        }else if (x==16 || x<42){
                            Layerbatch_StaticData.feedingmsg="0% Chick mash & 100% Chick crumble";
                        }
                        else if (x==16 || x<42){
                            Layerbatch_StaticData.feedingmsg="25% Chick crumble & 75% Growers mash";
                        }
                        else if (x==43){
                            Layerbatch_StaticData.feedingmsg="50% Chick crumble & 50% Growers mash";
                        }
                        else if (x==44){
                            Layerbatch_StaticData.feedingmsg="75% Chick crumble & 25% Growers mash";
                        }
                        else if (x==45){
                            Layerbatch_StaticData.feedingmsg="0% Chick crumble & 100% Growers mash";
                        }


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
        requestQueue.add(jsonArrayRequest2);
        //--------------------------------
        requestQueue.add(jsonArrayRequest);
        //------------------------------------------------------
        lrecrodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_layerbatchRecord.class);
                startActivity(intent);
            }
        });
        lcurrentstatusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_Layerbatchcurrentstatus.class);
                startActivity(intent);
            }
        });
        lvaccinationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Worker_layervaccination.class);
                startActivity(intent);
            }
        });
    }
}