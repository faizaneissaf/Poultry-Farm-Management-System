package com.example.poultryfarmmanagementsystem.Customer;

public class customer_layerbatches_list_tbl {
    public int clbid;
    public String clbname;
    public customer_layerbatches_list_tbl(){}

    public customer_layerbatches_list_tbl(int clbid, String clbname) {
        this.clbid = clbid;
        this.clbname = clbname;
    }

    public int getClbid() {
        return clbid;
    }

    public void setClbid(int clbid) {
        this.clbid = clbid;
    }

    public String getClbname() {
        return clbname;
    }

    public void setClbname(String clbname) {
        this.clbname = clbname;
    }
}
