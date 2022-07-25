package com.example.poultryfarmmanagementsystem.Admin;

public class customers_list_tbl {
    public String customername;
    public String customeremail;
    public String customerphone;
    public  String  customeraddress;
    public int customerid;
    public customers_list_tbl(){};

    public customers_list_tbl(String customername, String customeremail, String customerphone, String customeraddress, int customerid) {
        this.customername = customername;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
        this.customeraddress = customeraddress;
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
}
