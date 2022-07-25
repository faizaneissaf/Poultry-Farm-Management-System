package com.example.poultryfarmmanagementsystem.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class poultries_rv_adapter extends RecyclerView.Adapter<poultries_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<poultries_list_tbl> poultries;
    public poultries_rv_adapter(Context ctx, List<poultries_list_tbl> poultries){
        this.inflater=LayoutInflater.from(ctx);
        this.poultries=poultries;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.admin_poultries_list_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.pltryname.setText(poultries.get(position).getPltryname());
        holder.pltryaddress.setText(poultries.get(position).getPltryaddress());
    }

    @Override
    public int getItemCount() {
        return poultries.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView pltryname,pltryaddress;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            pltryname=itemView.findViewById(R.id.admin_pltry_name);
            pltryaddress=itemView.findViewById(R.id.admin_pltry_address);
        }
    }
}
