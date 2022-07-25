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
import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class worker_layer_batches_rv_adapter extends RecyclerView.Adapter<worker_layer_batches_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<worker_layer_batches_tbl_list> layerbatches;
    Context mContext;
    public worker_layer_batches_rv_adapter(Context ctx, List<worker_layer_batches_tbl_list> layerbatches){
        this.inflater=LayoutInflater.from(ctx);
        this.layerbatches=layerbatches;
    }
    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.worker_layerbatch_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.layerbtchname.setText(layerbatches.get(position).getLayer_batchname());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return layerbatches.size();
    }
    public class View_Holder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView layerbtchname;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            layerbtchname=itemView.findViewById(R.id.layer_batchname_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Layerbatch_StaticData.lb_id=layerbatches.get(position).getLayerbatch_id();
            mContext.startActivity(new Intent(mContext, Worker_Layerbatch_dashboard.class));
        }
    }
}
