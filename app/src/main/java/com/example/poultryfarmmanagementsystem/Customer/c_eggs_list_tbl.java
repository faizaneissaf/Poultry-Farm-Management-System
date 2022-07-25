package com.example.poultryfarmmanagementsystem.Customer;

public class c_eggs_list_tbl {
    public  int ce_saleid;
    public  int ce_lbid;
    public String  ce_bname;
    public int ce_totaleggsintrays;
    public  int ce_pltryid;
    public int ce_pricepertray;
    public  c_eggs_list_tbl(){}

    public c_eggs_list_tbl(int ce_saleid, int ce_lbid, String ce_bname, int ce_totaleggsintrays, int ce_pltryid, int ce_pricepertray) {
        this.ce_saleid = ce_saleid;
        this.ce_lbid = ce_lbid;
        this.ce_bname = ce_bname;
        this.ce_totaleggsintrays = ce_totaleggsintrays;
        this.ce_pltryid = ce_pltryid;
        this.ce_pricepertray = ce_pricepertray;
    }

    public int getCe_pricepertray() {
        return ce_pricepertray;
    }

    public void setCe_pricepertray(int ce_pricepertray) {
        this.ce_pricepertray = ce_pricepertray;
    }

    public int getCe_saleid() {
        return ce_saleid;
    }

    public void setCe_saleid(int ce_saleid) {
        this.ce_saleid = ce_saleid;
    }

    public int getCe_lbid() {
        return ce_lbid;
    }

    public void setCe_lbid(int ce_lbid) {
        this.ce_lbid = ce_lbid;
    }

    public String getCe_bname() {
        return ce_bname;
    }

    public void setCe_bname(String ce_bname) {
        this.ce_bname = ce_bname;
    }

    public int getCe_totaleggsintrays() {
        return ce_totaleggsintrays;
    }

    public void setCe_totaleggsintrays(int ce_totaleggsintrays) {
        this.ce_totaleggsintrays = ce_totaleggsintrays;
    }

    public int getCe_pltryid() {
        return ce_pltryid;
    }

    public void setCe_pltryid(int ce_pltryid) {
        this.ce_pltryid = ce_pltryid;
    }
}
