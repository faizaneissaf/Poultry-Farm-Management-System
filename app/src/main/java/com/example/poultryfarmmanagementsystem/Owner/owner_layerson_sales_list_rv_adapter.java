package com.example.poultryfarmmanagementsystem.Owner;

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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class owner_layerson_sales_list_rv_adapter extends RecyclerView.Adapter<owner_layerson_sales_list_rv_adapter.View_Holder> {
    LayoutInflater inflater;
    List<owner_layerson_sales_list_tbl> los_list;
    Context mContext;
    public owner_layerson_sales_list_rv_adapter(Context ctx, List<owner_layerson_sales_list_tbl> los_list){
        this.inflater=LayoutInflater.from(ctx);
        this.los_list=los_list;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.owner_lb_sales_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.los_bbname.setText("Batch Name :"+los_list.get(position).getLos_bname());
        holder.los_totalqty.setText("Total Qty :"+String.valueOf(los_list.get(position).getLos_totalqty()));
        holder.los_ageindays.setText("Age in Days :"+String.valueOf(los_list.get(position).getLos_ageindays()));
        holder.los_avgbodyweight.setText("Total Egg Trays :"+String.valueOf(los_list.get(position).getLos_avgbodyweight()));
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return los_list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView los_bbname,los_totalqty,los_ageindays,los_avgbodyweight;
        Button los_putonsale;
        EditText pricepertray;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            los_bbname=itemView.findViewById(R.id.los_bbname);
            los_totalqty=itemView.findViewById(R.id.los_bbtotalqty);
            los_ageindays=itemView.findViewById(R.id.los_ageindays);
            los_avgbodyweight=itemView.findViewById(R.id.los_avgbodyweight);
            los_putonsale=itemView.findViewById(R.id.los_putonsale_btn);
            pricepertray=itemView.findViewById(R.id.et_pricepertray);
            int x=los_list.get(position).getLos_lbid();
            //        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            los_putonsale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url=Poultry_static_data.ipadd+"/PoultryFarmManagementSystem/api/owner/lbSalestatusUpdate?lbid="+x+"&pricepertray="+pricepertray.getText().toString();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(v.getContext(), "Now On sale", Toast.LENGTH_SHORT).show();
                                    ((Activity)mContext).finish();
                                }
                            }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(stringRequest);
                }
            });
        }
    }
}
