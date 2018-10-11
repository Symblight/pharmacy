package com.datamodel;

import java.util.Date;

public class Preparation extends AbstractModel {
    private String title;
    private Supplier supplier;
    private AppointmentMedicines appointmentMedicines; // назначение медикаментов
    private Unit unit;
    private PlaceStorage placeStorage; // место хранения
    private Date releaseDate;
    private Date shelfLife; // срок годность
    private double cost; // стоимость
    private ModeRelease modeRelease;
    private int count;
    private WayApply wayApply;

    public Preparation(){}

    public Preparation(String title, Supplier supplier, AppointmentMedicines appointmentMedicines, Unit unit, PlaceStorage placeStorage, Date releaseDate, Date shelfLife, double cost, ModeRelease modeRelease, int count, WayApply wayApply) {
        this.title = title;
        this.supplier = supplier;
        this.appointmentMedicines = appointmentMedicines;
        this.unit = unit;
        this.placeStorage = placeStorage;
        this.releaseDate = releaseDate;
        this.shelfLife = shelfLife;
        this.cost = cost;
        this.modeRelease = modeRelease;
        this.count = count;
        this.wayApply = wayApply;
    }

    public void setTitle(String title) { this.title = title;}
    public String getTitle() {return this.title;}

    public void setSupplier(Supplier supplier) { this.supplier = supplier;}
    public Supplier getSupplier() {return this.supplier;}

    public void setAppointmentMedicines(AppointmentMedicines appointmentMedicines) { this.appointmentMedicines = appointmentMedicines;}
    public AppointmentMedicines getAppointmentMedicines() {return this.appointmentMedicines;}

    public void setUnit(Unit unit) { this.unit = unit;}
    public Unit getUnit() {return this.unit;}

    public void setPlaceStorage(PlaceStorage placeStorage) { this.placeStorage = placeStorage;}
    public PlaceStorage getPlaceStorage() {return this.placeStorage;}

    public void setReleaseDate(Date date) { this.releaseDate = date;}
    public Date getReleaseDate() {return this.releaseDate;}

    public void setShelfLife(Date date) { this.shelfLife = date;}
    public Date getShelfLife() {return this.shelfLife;}

    public void setCost(double cost) { this.cost = cost;}
    public double getCost() {return this.cost;}

    public void setModeRelease(ModeRelease modeRelease) { this.modeRelease = modeRelease;}
    public ModeRelease getModeRelease() {return this.modeRelease;}

    public void setCount(int count) { this.count = count;}
    public int getCount() {return this.count;}

    public void setWayApply(WayApply wayApply) { this.wayApply = wayApply;}
    public WayApply getWayApply() {return this.wayApply;}
}
