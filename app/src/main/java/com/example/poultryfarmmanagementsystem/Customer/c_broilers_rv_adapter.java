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
import com.example.poultryfarmmanagementsystem.Owner.Poultry_static_data;
import com.example.poultryfarmmanagementsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class c_broilers_rv_adapter extends RecyclerView.Adapter<c_broilers_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<c_broilers_list_tbl> broilers_list;
    Context mContext;
    public c_broilers_rv_adapter(Context ctx, List<c_broilers_list_tbl> broilers_list){
        this.inflater=LayoutInflater.from(ctx);
        this.broilers_list=broilers_list;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.c_broilers_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.cb_batchname.setText("Batch Name :"+broilers_list.get(position).getCb_bname());
        holder.cb_avgbwingm.setText("Avg Weight in Gm Per chick :"+String.valueOf(broilers_list.get(position).getCb_avgbodyweight()));
        holder.cb_totalavbchick.setText("Total Avb Quantity :"+String.valueOf(broilers_list.get(position).getCb_totalqty()));
        holder.cb_priceperchick.setText("Price Per Chick :"+String.valueOf(broilers_list.get(position).getCb_priceperchick()));
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return broilers_list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        EditText cb_cname,cb_cadd,cb_oqty;
        int position;
        Button btn_cb_ordernow;
        TextView cb_batchname,cb_avgbwingm,cb_priceperchick,cb_totalavbchick;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            btn_cb_ordernow=itemView.findViewById(R.id.btn_cb_ordernow);
            cb_batchname=itemView.findViewById(R.id.cb_batchname);
            cb_avgbwingm=itemView.findViewById(R.id.cb_avgwingm);
            cb_priceperchick=itemView.findViewById(R.id.cb_priceperchick);
            cb_totalavbchick=itemView.findViewById(R.id.cb_totalavbqty);
            cb_cname=itemView.findViewById(R.id.cb_customername);
            cb_cadd=itemView.findViewById(R.id.cb_customeradd);
            cb_oqty=itemView.findViewById(R.id.cb_orderqty);
            //        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            btn_cb_ordernow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject obj=new JSONObject();
                    try {
                        obj.put("sale_id",broilers_list.get(position).getCb_saleid());
                        obj.put("c_name",cb_cname.getText().toString());
                        obj.put("c_address",cb_cadd.getText().toString());
                        obj.put("c_qty",cb_oqty.getText().toString());
                        String url= customer_static_data.ipadd+"/PoultryFarmManagementSystem/api/customer/bcustomerOrderUpdate";
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
