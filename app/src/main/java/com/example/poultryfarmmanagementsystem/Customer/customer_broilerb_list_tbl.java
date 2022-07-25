package com.example.poultryfarmmanagementsystem.Customer;

public class customer_broilerb_list_tbl {
    public int cbbid;
    public String cbbname;
    public customer_broilerb_list_tbl(){}

    public customer_broilerb_list_tbl(int cbbid, String cbbname) {
        this.cbbid = cbbid;
        this.cbbname = cbbname;
    }

    public int getCbbid() {
        return cbbid;
    }

    public void setCbbid(int cbbid) {
        this.cbbid = cbbid;
    }

    public String getCbbname() {
        return cbbname;
    }

    public void setCbbname(String cbbname) {
        this.cbbname = cbbname;
    }
}
