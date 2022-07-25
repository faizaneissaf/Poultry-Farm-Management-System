package com.example.poultryfarmmanagementsystem.Admin;

public class poultries_list_tbl {
    public String pltryname;
    public  String pltryaddress;
    public int pltryfarmid;
    public poultries_list_tbl(){};

    public poultries_list_tbl(String pltryname, String pltryaddress, int pltryfarmid) {
        this.pltryname = pltryname;
        this.pltryaddress = pltryaddress;
        this.pltryfarmid = pltryfarmid;
    }

    public String getPltryname() {
        return pltryname;
    }

    public void setPltryname(String pltryname) {
        this.pltryname = pltryname;
    }

    public String getPltryaddress() {
        return pltryaddress;
    }

    public void setPltryaddress(String pltryaddress) {
        this.pltryaddress = pltryaddress;
    }

    public int getPltryfarmid() {
        return pltryfarmid;
    }

    public void setPltryfarmid(int pltryfarmid) {
        this.pltryfarmid = pltryfarmid;
    }
}
