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

import com.example.poultryfarmmanagementsystem.Owner.Poultry_static_data;
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class customer_layerbatches_rv_adapter extends RecyclerView.Adapter<customer_layerbatches_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<customer_layerbatches_list_tbl> lp_list;
    Context mContext;
    public customer_layerbatches_rv_adapter(Context ctx, List<customer_layerbatches_list_tbl> lp_list){
        this.inflater=LayoutInflater.from(ctx);
        this.lp_list=lp_list;
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
        holder.lpname.setText(lp_list.get(position).getClbname());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return lp_list.size();
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
//            Toast.makeText(v.getContext(), "Layer click", Toast.LENGTH_SHORT).show();
            customer_static_data.lbid=lp_list.get(position).getClbid();

            mContext.startActivity(new Intent(mContext, Customer_EggsSale.class));
        }
    }
}
