package com.datamodel;


public class Order extends AbstractModel {
    private Preparation preparation;
    private String unit;
    private int count;
    private double cost;
 //   private double result;

    public Order(){}

    public void setPreparation(Preparation title){this.preparation = title;}
    public Preparation getPreparation() { return this.preparation;}

    public void setUnit(String unit){this.unit = unit;}
    public String getUnit() { return this.unit;}

    public void setCount(int count){this.count = count;}
    public int getCount() { return this.count;}

    public void setCost(double cost){this.cost = cost;}
    public double getCost() { return this.cost;}

//    public void setResult(double result){this.result = result;}
//    public double getResult() { return this.result;}
}
