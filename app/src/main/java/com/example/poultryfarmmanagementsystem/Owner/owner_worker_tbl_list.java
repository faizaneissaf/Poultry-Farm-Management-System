package com.example.poultryfarmmanagementsystem.Owner;

public class owner_worker_tbl_list {
    public String worker_name;
    public String worker_email;
    public String worker_phone;
    public String worker_address;
    public int worker_id;
    public  owner_worker_tbl_list(){};

    public owner_worker_tbl_list(String worker_name, String worker_email, String worker_phone, String worker_address, int worker_id) {
        this.worker_name = worker_name;
        this.worker_email = worker_email;
        this.worker_phone = worker_phone;
        this.worker_address = worker_address;
        this.worker_id = worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_email() {
        return worker_email;
    }

    public void setWorker_email(String worker_email) {
        this.worker_email = worker_email;
    }

    public String getWorker_phone() {
        return worker_phone;
    }

    public void setWorker_phone(String worker_phone) {
        this.worker_phone = worker_phone;
    }

    public String getWorker_address() {
        return worker_address;
    }

    public void setWorker_address(String worker_address) {
        this.worker_address = worker_address;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }
}
