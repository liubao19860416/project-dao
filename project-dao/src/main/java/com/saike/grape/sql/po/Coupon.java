package com.saike.grape.sql.po;

import java.sql.Timestamp;

public class Coupon {
    private Long id;

    private String code;

    private String name;

    private Integer fitToMinKm;

    private Integer fitToMaxKm;

    private Integer fitToMinDaysUsed;

    private Integer fitToMaxDaysUsed;

    private String fitToEmissionVolume;

    private String fitToVehicleScope;

    private String fitToCity;

    private Timestamp beginDatetime;

    private Timestamp endDatetime;

    private Long validInDays;

    private Float moneyAmount;

    private String color1;

    private String color2;

    private String ruleBrief;

    private Boolean actived;

    private Boolean deleted;

    private Timestamp createdDatetime;

    private Timestamp updatedDatetime;

    private String fitToDealerCode;

    private Integer useKmValue;

    private String typeCode;

    private String ruleString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFitToMinKm() {
        return fitToMinKm;
    }

    public void setFitToMinKm(Integer fitToMinKm) {
        this.fitToMinKm = fitToMinKm;
    }

    public Integer getFitToMaxKm() {
        return fitToMaxKm;
    }

    public void setFitToMaxKm(Integer fitToMaxKm) {
        this.fitToMaxKm = fitToMaxKm;
    }

    public Integer getFitToMinDaysUsed() {
        return fitToMinDaysUsed;
    }

    public void setFitToMinDaysUsed(Integer fitToMinDaysUsed) {
        this.fitToMinDaysUsed = fitToMinDaysUsed;
    }

    public Integer getFitToMaxDaysUsed() {
        return fitToMaxDaysUsed;
    }

    public void setFitToMaxDaysUsed(Integer fitToMaxDaysUsed) {
        this.fitToMaxDaysUsed = fitToMaxDaysUsed;
    }

    public String getFitToEmissionVolume() {
        return fitToEmissionVolume;
    }

    public void setFitToEmissionVolume(String fitToEmissionVolume) {
        this.fitToEmissionVolume = fitToEmissionVolume == null ? null : fitToEmissionVolume.trim();
    }

    public String getFitToVehicleScope() {
        return fitToVehicleScope;
    }

    public void setFitToVehicleScope(String fitToVehicleScope) {
        this.fitToVehicleScope = fitToVehicleScope == null ? null : fitToVehicleScope.trim();
    }

    public String getFitToCity() {
        return fitToCity;
    }

    public void setFitToCity(String fitToCity) {
        this.fitToCity = fitToCity == null ? null : fitToCity.trim();
    }

    public Timestamp getBeginDatetime() {
        return beginDatetime;
    }

    public void setBeginDatetime(Timestamp beginDatetime) {
        this.beginDatetime = beginDatetime;
    }

    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Long getValidInDays() {
        return validInDays;
    }

    public void setValidInDays(Long validInDays) {
        this.validInDays = validInDays;
    }

    public Float getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1 == null ? null : color1.trim();
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2 == null ? null : color2.trim();
    }

    public String getRuleBrief() {
        return ruleBrief;
    }

    public void setRuleBrief(String ruleBrief) {
        this.ruleBrief = ruleBrief == null ? null : ruleBrief.trim();
    }

    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public String getFitToDealerCode() {
        return fitToDealerCode;
    }

    public void setFitToDealerCode(String fitToDealerCode) {
        this.fitToDealerCode = fitToDealerCode == null ? null : fitToDealerCode.trim();
    }

    public Integer getUseKmValue() {
        return useKmValue;
    }

    public void setUseKmValue(Integer useKmValue) {
        this.useKmValue = useKmValue;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString == null ? null : ruleString.trim();
    }
}