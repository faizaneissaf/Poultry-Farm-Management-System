package com.example.poultryfarmmanagementsystem.Owner;

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

import com.example.poultryfarmmanagementsystem.LoginPage;
import com.example.poultryfarmmanagementsystem.R;
import com.example.poultryfarmmanagementsystem.SignupPage;

import java.util.List;

public class owner_mypltry_rv_adapter extends RecyclerView.Adapter<owner_mypltry_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<owner_mypltry_tbl_list> pltries;
    Context mContext;
    public owner_mypltry_rv_adapter(Context ctx, List<owner_mypltry_tbl_list> pltries){
        this.inflater=LayoutInflater.from(ctx);
        this.pltries=pltries;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.owner_pltries_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull owner_mypltry_rv_adapter.View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.mypltry.setText(pltries.get(position).getPltry_Name());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return pltries.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView mypltry;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            mypltry=itemView.findViewById(R.id.pltry_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Poultry_static_data.pltry_id=pltries.get(position).getPltryid();
            Toast.makeText(v.getContext(), Poultry_static_data.pltry_id+"", Toast.LENGTH_SHORT).show();
            mContext.startActivity(new Intent(mContext, Owner_pltry_Dashboard.class));
        }
    }
}
