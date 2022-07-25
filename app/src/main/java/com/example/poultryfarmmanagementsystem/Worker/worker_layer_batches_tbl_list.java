package com.example.poultryfarmmanagementsystem.Worker;

public class worker_layer_batches_tbl_list {
    public  String layer_batchname;
    public int layerbatch_id;
    public  worker_layer_batches_tbl_list(){};

    public worker_layer_batches_tbl_list(String layer_batchname, int layerbatch_id) {
        this.layer_batchname = layer_batchname;
        this.layerbatch_id = layerbatch_id;
    }

    public String getLayer_batchname() {
        return layer_batchname;
    }

    public void setLayer_batchname(String layer_batchname) {
        this.layer_batchname = layer_batchname;
    }

    public int getLayerbatch_id() {
        return layerbatch_id;
    }

    public void setLayerbatch_id(int layerbatch_id) {
        this.layerbatch_id = layerbatch_id;
    }
}
