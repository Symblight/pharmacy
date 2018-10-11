package com.datamodel;

import java.util.Date;

public class Employee extends Person {
    private Date birthDay;
    private String address;
    private String numberPhone;
    private Education education;
    private Position position; // должность
    private Date startWork; // дата принятия
    private double salary; // зарплата

    public Employee(){}

    public Employee(Date birthDay, String address, String numberPhone, Education education, Position position, Date startWork, double salary){
        this.birthDay = birthDay;
        this.address = address;
        this.numberPhone = numberPhone;
        this.education = education;
        this.position = position;
        this.startWork = startWork;
        this.salary = salary;
    }

    public void setBirthDay(Date date) { this.birthDay = date;}
    public Date getBirthDay() {return this.birthDay;}

    public void setAddress(String address) { this.address = address;}
    public String getAddress() {return this.address;}

    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone;}
    public String getNumberPhone() {return this.numberPhone;}

    public void setEducation(Education education) { this.education = education;}
    public Education getEducation() {return this.education;}

    public void setPosition(Position position) { this.position = position;}
    public Position getPosition() {return this.position;}

    public void setStartWork(Date startWork) { this.startWork = startWork;}
    public Date getStartWork() {return this.startWork;}

    public void setSalary(double salary) { this.salary = salary;}
    public double getSalary() {return this.salary;}
}
