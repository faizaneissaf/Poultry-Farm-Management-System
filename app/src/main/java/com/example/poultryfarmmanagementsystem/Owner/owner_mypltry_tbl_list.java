package com.example.poultryfarmmanagementsystem.Owner;

public class owner_mypltry_tbl_list {
    public  String pltry_Name;

    public int getPltryid() {
        return pltryid;
    }

    public void setPltryid(int pltryid) {
        this.pltryid = pltryid;
    }

    public  int pltryid;
    public owner_mypltry_tbl_list(String pltry_Name){
        this.pltry_Name=pltry_Name;
    }

    public owner_mypltry_tbl_list() { }

    public String getPltry_Name() {
        return pltry_Name;
    }

    public void setPltry_Name(String pltry_Name) {
        this.pltry_Name = pltry_Name;
    }
}
