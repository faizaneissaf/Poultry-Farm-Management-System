package com.example.poultryfarmmanagementsystem.Customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.Worker.Layerbatch_StaticData;
import com.example.poultryfarmmanagementsystem.Worker.Worker_Layerbatch_dashboard;

import java.util.List;

public class customer_broilerb_rv_adapter extends RecyclerView.Adapter<customer_broilerb_rv_adapter.View_Holder> {
    LayoutInflater inflater;
    List<customer_broilerb_list_tbl> bb_list;
    Context mContext;
    public customer_broilerb_rv_adapter(Context ctx, List<customer_broilerb_list_tbl> bb_list){
        this.inflater=LayoutInflater.from(ctx);
        this.bb_list=bb_list;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.customer_poultries_row_list,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.lpname.setText(bb_list.get(position).getCbbname());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return bb_list.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView lpname;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            lpname=itemView.findViewById(R.id.cspltry_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

//            Toast.makeText(v.getContext(), "Broiler Click", Toast.LENGTH_SHORT).show();
            customer_static_data.bbid=bb_list.get(position).getCbbid();

            mContext.startActivity(new Intent(mContext, Customer_BroilersSale.class));
        }
    }
}
