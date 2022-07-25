package com.example.poultryfarmmanagementsystem.Worker;

public class worker_broiler_batches_tbl_list {
    public String  broiler_batchname;
    public  int broiler_batchid;
    public  worker_broiler_batches_tbl_list(){ };

    public worker_broiler_batches_tbl_list(String broiler_batchname, int broiler_batchid) {
        this.broiler_batchname = broiler_batchname;
        this.broiler_batchid = broiler_batchid;
    }

    public String getBroiler_batchname() {
        return broiler_batchname;
    }

    public void setBroiler_batchname(String broiler_batchname) {
        this.broiler_batchname = broiler_batchname;
    }

    public int getBroiler_batchid() {
        return broiler_batchid;
    }

    public void setBroiler_batchid(int broiler_batchid) {
        this.broiler_batchid = broiler_batchid;
    }
}
