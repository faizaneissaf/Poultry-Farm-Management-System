package com.example.poultryfarmmanagementsystem.Admin;

public class owners_list_tbl {
    public  int ownerid;
    public  String ownername;
    public  String ownerphone;
    public  String owneremail;
    public  String owneradd;
    public  owners_list_tbl(){};

    public owners_list_tbl(int ownerid, String ownername, String ownerphone, String owneremail, String owneradd) {
        this.ownerid = ownerid;
        this.ownername = ownername;
        this.ownerphone = ownerphone;
        this.owneremail = owneremail;
        this.owneradd = owneradd;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }

    public String getOwneremail() {
        return owneremail;
    }

    public void setOwneremail(String owneremail) {
        this.owneremail = owneremail;
    }

    public String getOwneradd() {
        return owneradd;
    }

    public void setOwneradd(String owneradd) {
        this.owneradd = owneradd;
    }
}
