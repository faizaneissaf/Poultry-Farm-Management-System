package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.poultryfarmmanagementsystem.R;

public class Worker_broilerbatchcurrentstatus extends AppCompatActivity {
    TextView cstage,tdeadchicks,tfeedingkg,tvaccinecst,tconsumedfeed,rch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_broilerbatchcurrentstatus);
        cstage=findViewById(R.id.bb_cstage);
        tdeadchicks=findViewById(R.id.bb_totaldchicks);
        tfeedingkg=findViewById(R.id.bb_cstoffeed);
        tvaccinecst=findViewById(R.id.bb_totalvcst);
        tconsumedfeed=findViewById(R.id.bb_totalcfeed);
        rch=findViewById(R.id.rch);

        int x=Broilerbatch_StaticData.bb_tage;
        if (x<=7){
            cstage.setText("Embryo");
        }else if (x>=8&&x<18){
            cstage.setText("Chick");
        }else if (x>=18&&x<28){
            cstage.setText("Pullet");
        }else{
            cstage.setText("Adult");
        }
        tdeadchicks.setText(Broilerbatch_StaticData.bb_mortality+"");
        tfeedingkg.setText("Rs : "+Broilerbatch_StaticData.bb_cstoffeedinkg+"");
        tvaccinecst.setText("Rs : "+Broilerbatch_StaticData.bb_totalvaccincst+"");
        tconsumedfeed.setText(Broilerbatch_StaticData.bb_totalfeedconsumedinkg+" KG");
        int rc=Broilerbatch_StaticData.bb_totalq-Broilerbatch_StaticData.bb_mortality;
        rch.setText(""+rc);

    }
}