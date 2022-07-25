package com.example.poultryfarmmanagementsystem.Owner;

public class owner_layerson_sales_list_tbl {
    public  int los_lbid;
    public  String los_bname;
    public  int los_totalqty;
    public  int los_ageindays;
    public  int los_mortality;
    public  int los_avgbodyweight;
    public  int los_pltryid;
    public owner_layerson_sales_list_tbl(){};

    public owner_layerson_sales_list_tbl(int los_bbid, String los_bname, int los_totalqty, int los_ageindays, int los_mortality, int los_avgbodyweight, int los_pltryid) {
        this.los_lbid = los_bbid;
        this.los_bname = los_bname;
        this.los_totalqty = los_totalqty;
        this.los_ageindays = los_ageindays;
        this.los_mortality = los_mortality;
        this.los_avgbodyweight = los_avgbodyweight;
        this.los_pltryid = los_pltryid;
    }

    public int getLos_lbid() {
        return los_lbid;
    }

    public void setLos_lbid(int los_lbid) {
        this.los_lbid = los_lbid;
    }

    public String getLos_bname() {
        return los_bname;
    }

    public void setLos_bname(String los_bname) {
        this.los_bname = los_bname;
    }

    public int getLos_totalqty() {
        return los_totalqty;
    }

    public void setLos_totalqty(int los_totalqty) {
        this.los_totalqty = los_totalqty;
    }

    public int getLos_ageindays() {
        return los_ageindays;
    }

    public void setLos_ageindays(int los_ageindays) {
        this.los_ageindays = los_ageindays;
    }

    public int getLos_mortality() {
        return los_mortality;
    }

    public void setLos_mortality(int los_mortality) {
        this.los_mortality = los_mortality;
    }

    public int getLos_avgbodyweight() {
        return los_avgbodyweight;
    }

    public void setLos_avgbodyweight(int los_avgbodyweight) {
        this.los_avgbodyweight = los_avgbodyweight;
    }

    public int getLos_pltryid() {
        return los_pltryid;
    }

    public void setLos_pltryid(int los_pltryid) {
        this.los_pltryid = los_pltryid;
    }
}
