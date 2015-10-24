package com.saike.grape.dao.entity.others;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;

/**
 * 保养券实体类
 */
@Alias("coupon")
public class Coupon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 569944897964392818L;

    private String name;                    // 保养券名称
    private Integer fitToMinKm;             // 适用最小公里数
    private Integer fitToMaxKm;             // 适用最大公里数
    private Integer fitToMinDaysUsed;       // 适用最小使用年限（天数）
    private Integer fitToMaxDaysUsed;       // 适用最小使用年限（天数）
    private String fitToEmissionVolume;     // 适用排量范围
    private String fitToVehicleScope;       // 适用车辆范围
    private String fitToCity;               // 适用城市编号
    private Timestamp beginDatetime;        // 起始日期
    private Timestamp endDatetime;          // 结束日期
    private Float moneyAmount;              // 金额
    private String color1;                  // 色值1
    private String color2;                  // 色值2
    private String ruleBrief;               // 规则简介
    private String ruleDetail;              // 规则详情,text格式自动转换
    private Integer validInDays;            // 有效期天数

    public Integer getValidInDays() {
		return validInDays;
	}

	public void setValidInDays(Integer validInDays) {
		this.validInDays = validInDays;
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
        this.fitToEmissionVolume = fitToEmissionVolume;
    }

    public String getFitToVehicleScope() {
        return fitToVehicleScope;
    }

    public void setFitToVehicleScope(String fitToVehicleScope) {
        this.fitToVehicleScope = fitToVehicleScope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFitToCity() {
        return fitToCity;
    }

    public void setFitToCity(String fitToCity) {
        this.fitToCity = fitToCity;
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
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getRuleBrief() {
        return ruleBrief;
    }

    public void setRuleBrief(String ruleBrief) {
        this.ruleBrief = ruleBrief;
    }

    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail;
    }

    @Override
    public String toString() {
        return "Coupon [name=" + name + ", fitToMinKm=" + fitToMinKm
                + ", fitToMaxKm=" + fitToMaxKm + ", fitToMinDaysUsed="
                + fitToMinDaysUsed + ", fitToMaxDaysUsed=" + fitToMaxDaysUsed
                + ", fitToEmissionVolume=" + fitToEmissionVolume
                + ", fitToVehicleScope=" + fitToVehicleScope + ", fitToCity="
                + fitToCity + ", beginDatetime=" + beginDatetime
                + ", endDatetime=" + endDatetime + ", moneyAmount="
                + moneyAmount + ", color1=" + color1 + ", color2=" + color2
                + ", ruleBrief=" + ruleBrief + ", ruleDetail=" + ruleDetail
                + "]";
    }

}
