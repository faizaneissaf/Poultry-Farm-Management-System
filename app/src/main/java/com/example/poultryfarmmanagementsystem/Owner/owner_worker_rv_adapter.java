package com.example.poultryfarmmanagementsystem.Owner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poultryfarmmanagementsystem.R;

import java.util.List;

public class owner_worker_rv_adapter extends RecyclerView.Adapter<owner_worker_rv_adapter.View_Holder>{
    LayoutInflater inflater;
    List<owner_worker_tbl_list> workers;
    Context mContext;
    public owner_worker_rv_adapter(Context ctx, List<owner_worker_tbl_list> workers){
        this.inflater=LayoutInflater.from(ctx);
        this.workers=workers;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.owner_worker_list_row,parent,false);
        mContext=parent.getContext();
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.workername.setText(workers.get(position).getWorker_name());
        holder.workeremail.setText(workers.get(position).getWorker_email());
        holder.workerphone.setText(workers.get(position).getWorker_phone());
        holder.workeraddress.setText(workers.get(position).getWorker_address());
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder{
        TextView workername,workeremail,workerphone,workeraddress;
        int position;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            workername=itemView.findViewById(R.id.tv_wname);
            workeremail=itemView.findViewById(R.id.tv_wemail);
            workerphone=itemView.findViewById(R.id.tv_wcontact);
            workeraddress=itemView.findViewById(R.id.tv_waddress);
        }
    }
}
