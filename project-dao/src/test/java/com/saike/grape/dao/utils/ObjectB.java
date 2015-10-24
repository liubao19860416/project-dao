package com.saike.grape.dao.utils;


import java.sql.Timestamp;

import com.saike.grape.dao.utils.DAOConstants.MaintenancePeriodUnit;

public class ObjectB {

    private String name;
    private int value;
    private String actived;

    private MaintenancePeriodUnit unit;
    
    private Timestamp updatedDatetime;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MaintenancePeriodUnit getUnit() {
        return unit;
    }

    public void setUnit(MaintenancePeriodUnit unit) {
        this.unit = unit;
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
    
    
}
