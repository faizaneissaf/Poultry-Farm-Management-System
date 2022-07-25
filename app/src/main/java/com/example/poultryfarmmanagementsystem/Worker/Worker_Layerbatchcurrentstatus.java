package com.example.poultryfarmmanagementsystem.Worker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.poultryfarmmanagementsystem.R;

public class Worker_Layerbatchcurrentstatus extends AppCompatActivity {
    TextView lbcstage,lbtdeadchicks,lbtfeedingkg,lbtvaccinecst,lbtconsumedfeed,tchicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_layerbatchcurrentstatus);
        lbcstage=findViewById(R.id.lb_cstage);
        lbtdeadchicks=findViewById(R.id.lb_totaldchicks);
        lbtfeedingkg=findViewById(R.id.lb_cstoffeed);
        lbtvaccinecst=findViewById(R.id.lb_totalvcst);
        lbtconsumedfeed=findViewById(R.id.lb_totalcfeed);
        tchicks=findViewById(R.id.totalcqty);
        int x=Broilerbatch_StaticData.bb_tage;
        if (x<=7){
            lbcstage.setText("Embryo");
        }else if (x>=8&&x<18){
            lbcstage.setText("Chick");
        }else if (x>=18&&x<28){
            lbcstage.setText("Pullet");
        }else{
            lbcstage.setText("Adult");
        }
        lbtdeadchicks.setText(Layerbatch_StaticData.lb_mortality+"");
        lbtfeedingkg.setText("Rs : "+Layerbatch_StaticData.lb_cstoffeedinkg+"");
        lbtvaccinecst.setText("Rs : "+Layerbatch_StaticData.lb_totalvaccincst+"");
        lbtconsumedfeed.setText(Layerbatch_StaticData.lb_totalfeedconsumedinkg+" KG");
        int r=Layerbatch_StaticData.lb_totalc-Layerbatch_StaticData.lb_mortality;
        tchicks.setText(r+"");
    }
}