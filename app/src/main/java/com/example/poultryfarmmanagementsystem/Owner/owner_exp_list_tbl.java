package com.example.poultryfarmmanagementsystem.Owner;

public class owner_exp_list_tbl {
    public String oe_batchname;
    public String oe_totalqty;
    public String oe_totalcst;
    public String oe_totalfeedcst;
    public String oe_totalvaccinecst;
    public owner_exp_list_tbl(){}

    public owner_exp_list_tbl(String oe_batchname, String oe_totalqty, String oe_totalcst, String oe_totalfeedcst, String oe_totalvaccinecst) {
        this.oe_batchname = oe_batchname;
        this.oe_totalqty = oe_totalqty;
        this.oe_totalcst = oe_totalcst;
        this.oe_totalfeedcst = oe_totalfeedcst;
        this.oe_totalvaccinecst = oe_totalvaccinecst;
    }

    public String getOe_batchname() {
        return oe_batchname;
    }

    public void setOe_batchname(String oe_batchname) {
        this.oe_batchname = oe_batchname;
    }

    public String getOe_totalqty() {
        return oe_totalqty;
    }

    public void setOe_totalqty(String oe_totalqty) {
        this.oe_totalqty = oe_totalqty;
    }

    public String getOe_totalcst() {
        return oe_totalcst;
    }

    public void setOe_totalcst(String oe_totalcst) {
        this.oe_totalcst = oe_totalcst;
    }

    public String getOe_totalfeedcst() {
        return oe_totalfeedcst;
    }

    public void setOe_totalfeedcst(String oe_totalfeedcst) {
        this.oe_totalfeedcst = oe_totalfeedcst;
    }

    public String getOe_totalvaccinecst() {
        return oe_totalvaccinecst;
    }

    public void setOe_totalvaccinecst(String oe_totalvaccinecst) {
        this.oe_totalvaccinecst = oe_totalvaccinecst;
    }
}
