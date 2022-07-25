package com.example.poultryfarmmanagementsystem.Owner;

public class owner_broilerson_sales_list_tbl {
    public  int bos_bbid;
    public  String bos_bname;
    public  int bos_totalqty;
    public  int bos_ageindays;
    public  int bos_mortality;
    public  int bos_avgbodyweight;
    public  int bos_pltryid;
    public owner_broilerson_sales_list_tbl(){}

    public owner_broilerson_sales_list_tbl(int bos_bbid, String bos_bname, int bos_totalqty, int bos_ageindays, int bos_mortality, int bos_avgbodyweight, int bos_pltryid) {
        this.bos_bbid = bos_bbid;
        this.bos_bname = bos_bname;
        this.bos_totalqty = bos_totalqty;
        this.bos_ageindays = bos_ageindays;
        this.bos_mortality = bos_mortality;
        this.bos_avgbodyweight = bos_avgbodyweight;
        this.bos_pltryid = bos_pltryid;
    }

    public int getBos_bbid() {
        return bos_bbid;
    }

    public void setBos_bbid(int bos_bbid) {
        this.bos_bbid = bos_bbid;
    }

    public String getBos_bname() {
        return bos_bname;
    }

    public void setBos_bname(String bos_bname) {
        this.bos_bname = bos_bname;
    }

    public int getBos_totalqty() {
        return bos_totalqty;
    }

    public void setBos_totalqty(int bos_totalqty) {
        this.bos_totalqty = bos_totalqty;
    }

    public int getBos_ageindays() {
        return bos_ageindays;
    }

    public void setBos_ageindays(int bos_ageindays) {
        this.bos_ageindays = bos_ageindays;
    }

    public int getBos_mortality() {
        return bos_mortality;
    }

    public void setBos_mortality(int bos_mortality) {
        this.bos_mortality = bos_mortality;
    }

    public int getBos_avgbodyweight() {
        return bos_avgbodyweight;
    }

    public void setBos_avgbodyweight(int bos_avgbodyweight) {
        this.bos_avgbodyweight = bos_avgbodyweight;
    }

    public int getBos_pltryid() {
        return bos_pltryid;
    }

    public void setBos_pltryid(int bos_pltryid) {
        this.bos_pltryid = bos_pltryid;
    }
}
