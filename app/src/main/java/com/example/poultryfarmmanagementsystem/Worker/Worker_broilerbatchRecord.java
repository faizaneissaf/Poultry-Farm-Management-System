package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker_broilerbatchRecord extends AppCompatActivity {
    EditText bbd_feed,bbd_cstoffeedinkg,bbd_vaccinename,bbd_totalvaccinecst,bbd_cnsumedfeedinkg,bbd_avgbodywingm,bbd_mortality;
    Button submit_btnbbd;
    String start_date,end_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_broilerbatch_record);
        bbd_feed=findViewById(R.id.et_feedtypebb);
        bbd_cstoffeedinkg=findViewById(R.id.cstoffeedinkg_bb);
        bbd_vaccinename=findViewById(R.id.vaccinenamebb);
        bbd_totalvaccinecst=findViewById(R.id.totalvcstbb);
        bbd_cnsumedfeedinkg=findViewById(R.id.consumedfeedinkg_bb);
        bbd_avgbodywingm=findViewById(R.id.avgbodyweightingrms_bb);
        bbd_mortality=findViewById(R.id.mortality_bb);
        submit_btnbbd=findViewById(R.id.btnsubmitdata_bb);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //------------
        submit_btnbbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    ////----------------Age in days
                    //Displaying current date and time in 12 hour format with AM/PM
                    DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
                    String dateString2 = dateFormat2.format(new Date()).toString();
                    start_date=Broilerbatch_StaticData.bb_arrivaldate;
                    end_date=dateString2;
                    //HH converts hour in 24 hours format (0-23), day calculation
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = format.parse(start_date);
                        d2 = format.parse(end_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long diff = Math.abs(d2.getTime() - d1.getTime());
                    long diffDays = diff / (24 * 60 * 60 * 1000);
//                    Toast.makeText(getApplicationContext(), String.valueOf(diffDays)+"", Toast.LENGTH_SHORT).show();
                    int tage=(int)diffDays;
                    Broilerbatch_StaticData.bb_ageindays=tage;
                    //----------------------------
                    obj.put("feed_type",bbd_feed.getText().toString());
                    obj.put("costoffeedinkg",bbd_cstoffeedinkg.getText().toString());
                    obj.put("vaccine_name",bbd_vaccinename.getText().toString());
                    obj.put("totalvaccine_cost",bbd_totalvaccinecst.getText().toString());
                    obj.put("totalfeedconsumedinkg",bbd_cnsumedfeedinkg.getText().toString());
                    obj.put("avgbodyweightingrams",bbd_avgbodywingm.getText().toString());
                    obj.put("mortality",bbd_mortality.getText().toString());
                    obj.put("ageindays",Broilerbatch_StaticData.bb_ageindays);
                    obj.put("bb_id",Broilerbatch_StaticData.bb_id);
                    obj.put("pltry_id",Broilerbatch_StaticData.bb_pltryid);
                    //----------------------------
                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/addBroilerData";
//                    Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), obj+"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Record Added Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    requestQueue.add(jsonObjectRequest);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}