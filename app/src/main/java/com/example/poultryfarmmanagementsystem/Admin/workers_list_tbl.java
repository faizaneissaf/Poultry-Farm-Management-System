package com.example.poultryfarmmanagementsystem.Admin;

public class workers_list_tbl {
    public  String workername;
    public  String workeremail;
    public  String workerphone;
    public String workeradd;
    public int workerid;
    public  workers_list_tbl(){};

    public workers_list_tbl(String workername, String workeremail, String workerphone, String workeradd, int workerid) {
        this.workername = workername;
        this.workeremail = workeremail;
        this.workerphone = workerphone;
        this.workeradd = workeradd;
        this.workerid = workerid;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public String getWorkeremail() {
        return workeremail;
    }

    public void setWorkeremail(String workeremail) {
        this.workeremail = workeremail;
    }

    public String getWorkerphone() {
        return workerphone;
    }

    public void setWorkerphone(String workerphone) {
        this.workerphone = workerphone;
    }

    public String getWorkeradd() {
        return workeradd;
    }

    public void setWorkeradd(String workeradd) {
        this.workeradd = workeradd;
    }

    public int getWorkerid() {
        return workerid;
    }

    public void setWorkerid(int workerid) {
        this.workerid = workerid;
    }
}
