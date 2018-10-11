package com.datamodel;

import java.util.Date;

public class Producer extends AbstractModel {
    private String title;
    private String address;
    private Date dateCreate;
    private String numberPhone;
    private String email;

    public Producer(){}

    public Producer(String title, String address, Date dateCreate, String numberPhone, String email){
        this.title = title;
        this.address = address;
        this.dateCreate = dateCreate;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}

    public void setAddress(String address) { this.address = address;}
    public String getAddress() {return this.address;}

    public void setDateCreate(Date date) { this.dateCreate = date;}
    public Date getDateCreate() {return this.dateCreate;}

    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone;}
    public String getNumberPhone() {return this.numberPhone;}

    public void setEmail(String email) { this.email = email;}
    public String getEmail() {return this.email;}
}
