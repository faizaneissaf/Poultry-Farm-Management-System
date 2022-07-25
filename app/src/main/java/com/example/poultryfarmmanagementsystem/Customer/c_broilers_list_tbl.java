package com.example.poultryfarmmanagementsystem.Customer;

public class c_broilers_list_tbl {
    public  int cb_saleid;
    public  int cb_bbid;
    public String  cb_bname;
    public int cb_totalqty;
    public int cb_avgbodyweight;
    public  int cb_pltryid;
    public int cb_priceperchick;
    public c_broilers_list_tbl(){}

    public c_broilers_list_tbl(int cb_saleid, int cb_bbid, String cb_bname, int cb_totalqty, int cb_avgbodyweight, int cb_pltryid, int cb_priceperchick) {
        this.cb_saleid = cb_saleid;
        this.cb_bbid = cb_bbid;
        this.cb_bname = cb_bname;
        this.cb_totalqty = cb_totalqty;
        this.cb_avgbodyweight = cb_avgbodyweight;
        this.cb_pltryid = cb_pltryid;
        this.cb_priceperchick = cb_priceperchick;
    }

    public int getCb_saleid() {
        return cb_saleid;
    }

    public void setCb_saleid(int cb_saleid) {
        this.cb_saleid = cb_saleid;
    }

    public int getCb_bbid() {
        return cb_bbid;
    }

    public void setCb_bbid(int cb_bbid) {
        this.cb_bbid = cb_bbid;
    }

    public String getCb_bname() {
        return cb_bname;
    }

    public void setCb_bname(String cb_bname) {
        this.cb_bname = cb_bname;
    }

    public int getCb_totalqty() {
        return cb_totalqty;
    }

    public void setCb_totalqty(int cb_totalqty) {
        this.cb_totalqty = cb_totalqty;
    }

    public int getCb_avgbodyweight() {
        return cb_avgbodyweight;
    }

    public void setCb_avgbodyweight(int cb_avgbodyweight) {
        this.cb_avgbodyweight = cb_avgbodyweight;
    }

    public int getCb_pltryid() {
        return cb_pltryid;
    }

    public void setCb_pltryid(int cb_pltryid) {
        this.cb_pltryid = cb_pltryid;
    }

    public int getCb_priceperchick() {
        return cb_priceperchick;
    }

    public void setCb_priceperchick(int cb_priceperchick) {
        this.cb_priceperchick = cb_priceperchick;
    }
}
