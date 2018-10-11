package com.datamodel;

import java.util.Date;

public class Recede extends AbstractModel {
    private String typeInvoice;
    private Date dateComing;
    private PlaceStorage placeStorage;
    private Supplier supplier;
    private Preparation preparation;
    private int count;
    private double cost;

    public Recede(){}

    public Recede(String typeInvoice, Date dateComing, PlaceStorage placeStorage, Supplier supplier, Preparation preparation, int count, double cost){
        this.typeInvoice = typeInvoice;
        this.dateComing = dateComing;
        this.placeStorage = placeStorage;
        this.supplier = supplier;
        this.preparation = preparation;
        this.count = count;
        this.cost = cost;
    }

    public void setTypeInvoice(String typeInvoice) { this.typeInvoice = typeInvoice;}
    public String getTypeInvoice() {return this.typeInvoice;}

    public void setDateComing(Date date) { this.dateComing = date;}
    public Date getDateComing() {return this.dateComing;}

    public void setPlaceStorage(PlaceStorage placeStorage) { this.placeStorage = placeStorage;}
    public PlaceStorage getPlaceStorage() {return this.placeStorage;}

    public void setSupplier(Supplier supplier) { this.supplier = supplier;}
    public Supplier getSupplier() {return this.supplier;}

    public void setPreparation(Preparation preparation) { this.preparation = preparation;}
    public Preparation getPreparation() {return this.preparation;}

    public void setCount(int count) { this.count = count;}
    public int getCount() {return this.count;}

    public void setCost(double cost) { this.cost = cost;}
    public double getCost() {return this.cost;}

}
