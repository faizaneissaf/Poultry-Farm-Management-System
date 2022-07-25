package com.example.poultryfarmmanagementsystem.Customer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.Admin.poultries_list_tbl;
import com.example.poultryfarmmanagementsystem.Admin.poultries_rv_adapter;
import com.example.poultryfarmmanagementsystem.Owner.owner_broilerson_sales_list_rv_adapter;
import com.example.poultryfarmmanagementsystem.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class c_eggs_list_rv_adapter extends RecyclerView.Adapter<c_eggs_list_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<c_eggs_list_tbl> eggs_list;
    Context mContext;
    public c_eggs_list_rv_adapter(Context ctx, List<c_eggs_list_tbl> eggs_list){
        this.inflater=LayoutInflater.from(ctx);
        this.eggs_list=eggs_list;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.c_eggs_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.ce_batchname.setText("Batch Name :"+eggs_list.get(position).getCe_bname());
        holder.ce_avbtrays.setText("Avb Egg Trays :"+String.valueOf(eggs_list.get(position).getCe_totaleggsintrays()));
        holder.ce_pricepertray.setText("Price Per Tray :"+String.valueOf(eggs_list.get(position).getCe_pricepertray()));
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return eggs_list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        EditText ce_cname,ce_cadd,ce_oqty;
        int position;
        Button btnoredernow;
        TextView ce_batchname,ce_avbtrays,ce_pricepertray;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            btnoredernow=itemView.findViewById(R.id.btn_ce_order);
            ce_batchname=itemView.findViewById(R.id.ce_batchname);
            ce_avbtrays=itemView.findViewById(R.id.ce_eggstrays);
            ce_pricepertray=itemView.findViewById(R.id.ce_pricepertray);
            ce_cname=itemView.findViewById(R.id.ce_customername);
            ce_cadd=itemView.findViewById(R.id.ce_customeradd);
            ce_oqty=itemView.findViewById(R.id.ce_orderqty);
            //        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            btnoredernow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject obj=new JSONObject();
                    try {
                        obj.put("sale_id",eggs_list.get(position).getCe_saleid());
                        obj.put("c_name",ce_cname.getText().toString());
                        obj.put("c_address",ce_cadd.getText().toString());
                        obj.put("c_qty",ce_oqty.getText().toString());
                        String url= customer_static_data.ipadd+"/PoultryFarmManagementSystem/api/customer/lcustomerOrderUpdate";
//                    Toast.makeText(getApplicationContext(), url+"", Toast.LENGTH_SHORT).show();
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                                Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(v.getContext(), "Order Filled", Toast.LENGTH_SHORT).show();
                                ((Activity)mContext).finish();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
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
}
