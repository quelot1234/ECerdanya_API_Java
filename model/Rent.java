package model;

import java.util.Date;

public class Rent {

    private int id;
    private int userId;
    private int houseId;
    private Date initialDate;
    private Date finalDate;
    private int nights;
    private int numPersons;
    private double price;

    public Rent(int userId, int houseId, Date initialDate, Date finalDate, int nights, int numPersons, double price) {
        this.userId = userId;
        this.houseId = houseId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.nights = nights;
        this.numPersons = numPersons;
        this.price = price;
    }

    public Rent() {

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getHouseId() {
        return houseId;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public int getNights() {
        return nights;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", userId=" + userId + ", houseId=" + houseId + ", "
                + "initialDate=" + initialDate + ", finalDate=" + finalDate + ", nights=" + nights + ", "
                + "numPersons=" + numPersons + ", price=" + price + '}';
    }
}
