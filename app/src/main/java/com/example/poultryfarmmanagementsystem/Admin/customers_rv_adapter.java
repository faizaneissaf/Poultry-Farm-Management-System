package com.example.poultryfarmmanagementsystem.Admin;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.Customer.customer_static_data;
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class customers_rv_adapter extends RecyclerView.Adapter<customers_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<customers_list_tbl> customers;
    public customers_rv_adapter(Context ctx, List<customers_list_tbl> customers){
        this.inflater=LayoutInflater.from(ctx);
        this.customers=customers;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.admin_customers_list_tbl_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.cname.setText(customers.get(position).getCustomername());
        holder.cemail.setText(customers.get(position).getCustomeremail());
        holder.cphone.setText(customers.get(position).getCustomerphone());
        holder.caddress.setText(customers.get(position).getCustomeraddress());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView cname,cphone,cemail,caddress;
        EditText passresetc;
        Button resetpc;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            cname=itemView.findViewById(R.id.cutomer_name);
            cphone=itemView.findViewById(R.id.cutomer_phoneno);
            cemail=itemView.findViewById(R.id.cutomer_email);
            caddress=itemView.findViewById(R.id.cutomer_address);
            passresetc=itemView.findViewById(R.id.passresetc);
            resetpc=itemView.findViewById(R.id.btnrpassc);
//            resetpc.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), passresetc.getText().toString()+"", Toast.LENGTH_SHORT).show();
//                }
//            });
//            Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            resetpc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), passresetw.getText().toString()+" and email ="+workeremail2.getText().toString(), Toast.LENGTH_SHORT).show();
                    String url = customer_static_data.ipadd+"/PoultryFarmManagementSystem/api/Admin/resetcpass?wemail="+cemail.getText().toString()+"&pass="+passresetc.getText().toString();
                    StringRequest stringRequest=new StringRequest(
                            com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(v.getContext(), "Password Reset", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(v.getContext(), "Not Working", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(stringRequest);
                }
            });
        }
    }
}
