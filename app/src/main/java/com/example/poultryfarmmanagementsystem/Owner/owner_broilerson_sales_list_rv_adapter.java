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

public class owner_broilerson_sales_list_rv_adapter extends RecyclerView.Adapter<owner_broilerson_sales_list_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<owner_broilerson_sales_list_tbl> bos_list;
    Context mContext;
    public owner_broilerson_sales_list_rv_adapter(Context ctx, List<owner_broilerson_sales_list_tbl> bos_list){
        this.inflater=LayoutInflater.from(ctx);
        this.bos_list=bos_list;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.owner_bb_sales_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.bos_bbname.setText("Batch Name :"+bos_list.get(position).getBos_bname());
        holder.bos_totalqty.setText("Total Qty :"+String.valueOf(bos_list.get(position).getBos_totalqty()));
        holder.bos_ageindays.setText("Age in Days :"+String.valueOf(bos_list.get(position).getBos_ageindays()));
        holder.bos_avgbodyweight.setText("Avg Body Weight in gm:"+String.valueOf(bos_list.get(position).getBos_avgbodyweight()));
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return bos_list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView bos_bbname,bos_totalqty,bos_ageindays,bos_avgbodyweight;
        Button bos_putonsale;
        EditText priceperchick;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            bos_bbname=itemView.findViewById(R.id.bos_bbname);
            bos_totalqty=itemView.findViewById(R.id.bos_bbtotalqty);
            bos_ageindays=itemView.findViewById(R.id.bos_ageindays);
            bos_avgbodyweight=itemView.findViewById(R.id.bos_avgbodyweight);
            bos_putonsale=itemView.findViewById(R.id.bos_putonsale_btn);
            priceperchick=itemView.findViewById(R.id.bos_price_perchick);

            //        Starting Request Queue
            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
            requestQueue.start();
            bos_putonsale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Poultry_static_data.bos_bb_id=bos_list.get(position).getBos_bbid();
                    String url=Poultry_static_data.ipadd+"/PoultryFarmManagementSystem/api/owner/bbSalestatusUpdate?bbid="+Poultry_static_data.bos_bb_id+"&priceperchick="+priceperchick.getText().toString();
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
