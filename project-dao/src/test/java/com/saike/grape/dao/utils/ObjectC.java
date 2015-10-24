package com.saike.grape.dao.utils;


import java.sql.Timestamp;

import com.saike.grape.dao.utils.DAOConstants.MaintenancePeriodUnit;

public class ObjectC {

    private String name;
    private String value;
    private String floatValue;
    
    private String datetime;
    
    private Timestamp createdDatetime;

    private MaintenancePeriodUnit unit;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaintenancePeriodUnit getUnit() {
        return unit;
    }

    public void setUnit(MaintenancePeriodUnit unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(String floatValue) {
        this.floatValue = floatValue;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
    
    
}
