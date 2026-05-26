package com.practice.May_June_26;

public class Customer {
    private String name;
    private String photoId;
    private long mobNo;
    boolean isMealTaken;
    int tableNo;
    public String getName() {
        return name;
    }

    public long getMobNo() {
        return mobNo;
    }



    public String getPhotoId() {
        return photoId;
    }

    public boolean isMealTaken() {
        return isMealTaken;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setMobNo(long mobNo) {
        this.mobNo = mobNo;
    }

    public void setMealTaken(boolean mealTaken) {
        isMealTaken = mealTaken;
    }

    public Customer(String name, String photoId, long mobNo, boolean isMealTaken) {
        this.name = name;
        this.photoId = photoId;
        this.mobNo = mobNo;
        this.isMealTaken = isMealTaken;
    }

    public int getTableNo() {

        return tableNo; }
}
