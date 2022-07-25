package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.Owner.Poultry_static_data;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.User_Information;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Worker_AddnewBatch extends AppCompatActivity {
    Button addbatchsubmit;
    Calendar calendar;
    TextView arrivaldate;
    EditText bbatchname,bbatchtotalqty,bbatchtotalcst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_addnew_batch);
        addbatchsubmit=findViewById(R.id.btn_addbatch_broiler);
        arrivaldate=findViewById(R.id.arrivaldate_txt_broiler);
        bbatchname=findViewById(R.id.batchname_broiler);
        bbatchtotalqty=findViewById(R.id.totalqty_broiler);
        bbatchtotalcst=findViewById(R.id.totalcst_broiler);
        //        Starting Request Queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        //------------
        addbatchsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj=new JSONObject();
                try {
                    obj.put("worker_id", Worker_StaticData.worker_id);
                    obj.put("bb_name",bbatchname.getText().toString());
                    obj.put("bb_arrivaldate",arrivaldate.getText().toString());
                    obj.put("bb_totalqty",bbatchtotalqty.getText().toString());
                    obj.put("bb_totalcost",bbatchtotalcst.getText().toString());
                    obj.put("pltry_id", Worker_StaticData.pltryid);
                    String url=getString(R.string.ip)+"/PoultryFarmManagementSystem/api/Worker/addBroilerBatch";
//                    Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Intent intent=new Intent(getApplicationContext(),Worker_Dashboard.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
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
        //--------------Date Picker
        calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateCalender();
            }
            private  void updateCalender(){
                String format="MM/dd/yyyy";
                SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.US);
                arrivaldate.setText(sdf.format(calendar.getTime()));
            }
        };
        arrivaldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Worker_AddnewBatch.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }
}