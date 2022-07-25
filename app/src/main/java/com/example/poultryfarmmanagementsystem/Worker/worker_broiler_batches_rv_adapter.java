package com.example.poultryfarmmanagementsystem.Worker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmmanagementsystem.Owner.Owner_pltry_Dashboard;
import com.example.poultryfarmmanagementsystem.Owner.owner_mypltry_rv_adapter;
import com.example.poultryfarmmanagementsystem.Owner.owner_mypltry_tbl_list;
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class worker_broiler_batches_rv_adapter extends RecyclerView.Adapter<worker_broiler_batches_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<worker_broiler_batches_tbl_list> broilerbatches;
    Context mContext;
    public worker_broiler_batches_rv_adapter(Context ctx, List<worker_broiler_batches_tbl_list> broilerbatches){
        this.inflater=LayoutInflater.from(ctx);
        this.broilerbatches=broilerbatches;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.worker_broilerbatch_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.broilerbtchname.setText(broilerbatches.get(position).getBroiler_batchname());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return broilerbatches.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView broilerbtchname;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            broilerbtchname=itemView.findViewById(R.id.broiler_batchname_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Broilerbatch_StaticData.bb_id=broilerbatches.get(position).getBroiler_batchid();
            mContext.startActivity(new Intent(mContext, Worker_Batch_Dashboard.class));
        }
    }
}
