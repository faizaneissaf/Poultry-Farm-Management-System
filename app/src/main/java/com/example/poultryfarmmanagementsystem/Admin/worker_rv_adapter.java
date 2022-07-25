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
import com.example.poultryfarmmanagementsystem.Owner.owner_worker_rv_adapter;
import com.example.poultryfarmmanagementsystem.Owner.owner_worker_tbl_list;
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class worker_rv_adapter extends RecyclerView.Adapter<worker_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<workers_list_tbl> workers2;
    public worker_rv_adapter(Context ctx, List<workers_list_tbl> workers2){
        this.inflater=LayoutInflater.from(ctx);
        this.workers2=workers2;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.admin_worker_list_tbl_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.workername2.setText(workers2.get(position).getWorkername());
        holder.workeremail2.setText(workers2.get(position).getWorkeremail());
        holder.workerphone2.setText(workers2.get(position).getWorkerphone());
        holder.workeradd2.setText(workers2.get(position).getWorkeradd());
    }

    @Override
    public int getItemCount() {
        return workers2.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView workername2,workerphone2,workeremail2,workeradd2;
        EditText passresetw;
        Button btnresetw;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            workername2=itemView.findViewById(R.id.worker_name2);
            workerphone2=itemView.findViewById(R.id.worker_phone2);
            workeremail2=itemView.findViewById(R.id.worker_email2);
            workeradd2=itemView.findViewById(R.id.worker_address2);
            passresetw=itemView.findViewById(R.id.passresetw);
            btnresetw=itemView.findViewById(R.id.btnrpassw);
//        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            btnresetw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), passresetw.getText().toString()+" and email ="+workeremail2.getText().toString(), Toast.LENGTH_SHORT).show();
                    String url = customer_static_data.ipadd+"/PoultryFarmManagementSystem/api/Admin/resetworkerpass?wemail="+workeremail2.getText().toString()+"&pass="+passresetw.getText().toString();
                    StringRequest stringRequest=new StringRequest(
                            Request.Method.GET, url, new Response.Listener<String>() {
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
