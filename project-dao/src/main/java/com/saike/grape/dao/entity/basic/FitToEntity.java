package com.saike.grape.dao.entity.basic;

import org.apache.ibatis.type.Alias;

/**
 * 该类主要用来作为持有保养项目以'fitTo'开头字段的公用类; <br/>
 * 该类中的字段仅作为insert和select时使用
 * 
 */
@Alias( "fitToEntity" )
public abstract class FitToEntity extends BaseEntity {

    private static final long serialVersionUID = -8350935376019040524L;

    private int fitToMinKm;             // 适用最小公里数
    private int fitToMaxKm;             // 适用最大公里数
    private int fitToMinDaysUsed;       // 适用最小使用年限（按天数）
    private int fitToMaxDaysUsed;       // 适用最大使用年限（按天数）
    private String fitToEmissionVolume; // 适用排量范围
    private String fitToVehicleScope;   // 适用车辆范围
    private String fitToCity;           // 适用城市范围

    public int getFitToMinKm() {
        return fitToMinKm;
    }

    public void setFitToMinKm(int fitToMinKm) {
        this.fitToMinKm = fitToMinKm;
    }

    public int getFitToMaxKm() {
        return fitToMaxKm;
    }

    public void setFitToMaxKm(int fitToMaxKm) {
        this.fitToMaxKm = fitToMaxKm;
    }

    public int getFitToMinDaysUsed() {
        return fitToMinDaysUsed;
    }

    public void setFitToMinDaysUsed(int fitToMinDaysUsed) {
        this.fitToMinDaysUsed = fitToMinDaysUsed;
    }

    public int getFitToMaxDaysUsed() {
        return fitToMaxDaysUsed;
    }

    public void setFitToMaxDaysUsed(int fitToMaxDaysUsed) {
        this.fitToMaxDaysUsed = fitToMaxDaysUsed;
    }

    public String getFitToEmissionVolume() {
        return fitToEmissionVolume;
    }

    public void setFitToEmissionVolume(String fitToEmissionVolume) {
        this.fitToEmissionVolume = fitToEmissionVolume;
    }

    public String getFitToVehicleScope() {
        return fitToVehicleScope;
    }

    public void setFitToVehicleScope(String fitToVehicleScope) {
        this.fitToVehicleScope = fitToVehicleScope;
    }

    public String getFitToCity() {
        return fitToCity;
    }

    public void setFitToCity(String fitToCity) {
        this.fitToCity = fitToCity;
    }

}
