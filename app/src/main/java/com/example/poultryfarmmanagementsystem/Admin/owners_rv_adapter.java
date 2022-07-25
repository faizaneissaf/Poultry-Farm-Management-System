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

public class owners_rv_adapter extends RecyclerView.Adapter<owners_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<owners_list_tbl> owners;
    public owners_rv_adapter(Context ctx, List<owners_list_tbl> owners){
        this.inflater=LayoutInflater.from(ctx);
        this.owners=owners;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.admin_owners_list_row,parent,false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.ownername.setText(owners.get(position).getOwnername());
        holder.owneremail.setText(owners.get(position).getOwneremail());
        holder.ownerphone.setText(owners.get(position).getOwnerphone());
        holder.owneradd.setText(owners.get(position).getOwneradd());
    }

    @Override
    public int getItemCount() {
        return owners.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView ownername,owneremail,owneradd,ownerphone;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            ownername=itemView.findViewById(R.id.owner_name);
            owneremail=itemView.findViewById(R.id.owner_email);
            ownerphone=itemView.findViewById(R.id.owner_phone);
            owneradd=itemView.findViewById(R.id.owner_address);
        }
    }
}
