package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AppCompatActivity;

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

public class Worker_layerbatchRecord extends AppCompatActivity {
    EditText lbfeedtype,lbvaccinename,lbcstoffeedinkg,lbtotalfeedconsumed,lbtotalvaccinecst,lbmortality,lbeggsproduced;
    Button btnsubmitrecord;
    String start_date2,end_date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_layerbatch_record);
        lbfeedtype=findViewById(R.id.lb_feedtype);
        lbcstoffeedinkg=findViewById(R.id.lb_cstoffeedinkg);
        lbvaccinename=findViewById(R.id.lb_vaccinename);
        lbtotalvaccinecst=findViewById(R.id.lb_vaccinecst);
        lbtotalfeedconsumed=findViewById(R.id.lb_cnsmdfeedinkg);
        lbeggsproduced=findViewById(R.id.lb_eggsproduced);
        lbmortality=findViewById(R.id.lb_mortality);
        btnsubmitrecord=findViewById(R.id.lb_rcrdsubmitbtn);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //------------
        btnsubmitrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    ////----------------Age in days
                    //Displaying current date and time in 12 hour format with AM/PM
                    DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
                    String dateString2 = dateFormat2.format(new Date()).toString();
                    start_date2=Layerbatch_StaticData.lb_arrivaldate;
                    end_date2=dateString2;
                    //HH converts hour in 24 hours format (0-23), day calculation
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = format.parse(start_date2);
                        d2 = format.parse(end_date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long diff = Math.abs(d2.getTime() - d1.getTime());
                    long diffDays = diff / (24 * 60 * 60 * 1000);
//                    Toast.makeText(getApplicationContext(), String.valueOf(diffDays)+"", Toast.LENGTH_SHORT).show();
                    int tage=(int)diffDays;
                    Layerbatch_StaticData.lb_ageindays=tage;
                    //----------------------------
                    obj.put("feed_type",lbfeedtype.getText().toString());
                    obj.put("costoffeedinkg",lbcstoffeedinkg.getText().toString());
                    obj.put("vaccine_name",lbvaccinename.getText().toString());
                    obj.put("totalvaccine_cost",lbtotalvaccinecst.getText().toString());
                    obj.put("totalfeedconsumedinkg",lbtotalfeedconsumed.getText().toString());
                    obj.put("eggs_produced",lbeggsproduced.getText().toString());
                    obj.put("mortality",lbmortality.getText().toString());
                    obj.put("ageindays",Layerbatch_StaticData.lb_ageindays);
                    obj.put("lb_id",Layerbatch_StaticData.lb_id);
                    obj.put("pltry_id",Layerbatch_StaticData.lb_pltryid);
                    //----------------------------
                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/addLayerData";
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