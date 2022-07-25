package com.example.poultryfarmmanagementsystem.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_rv_adapter;
import com.example.poultryfarmmanagementsystem.Worker.worker_broiler_batches_tbl_list;

import java.util.List;

public class owner_exp_list_rv_adapter extends RecyclerView.Adapter<owner_exp_list_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<owner_exp_list_tbl> explist;
    public owner_exp_list_rv_adapter(Context ctx, List<owner_exp_list_tbl> explist){
        this.inflater=LayoutInflater.from(ctx);
        this.explist=explist;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.owner_exp_list_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.oe_bname.setText(explist.get(position).getOe_batchname());
        holder.oe_totalqty.setText(explist.get(position).getOe_totalqty());
        holder.oe_totalcst.setText(explist.get(position).getOe_totalcst());
        holder.oe_totalfcst.setText(explist.get(position).getOe_totalfeedcst());
        holder.oe_totalvaccinecst.setText(explist.get(position).getOe_totalvaccinecst());
    }

    @Override
    public int getItemCount() {
        return explist.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView oe_bname,oe_totalqty,oe_totalcst,oe_totalfcst,oe_totalvaccinecst;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            oe_bname=itemView.findViewById(R.id.oe_batch_name);
            oe_totalqty=itemView.findViewById(R.id.oe_totalchicks);
            oe_totalcst=itemView.findViewById(R.id.oe_totalcst);
            oe_totalfcst=itemView.findViewById(R.id.oe_totalfeedcst);
            oe_totalvaccinecst=itemView.findViewById(R.id.oe_totalvaccinecst);
        }
    }
}
