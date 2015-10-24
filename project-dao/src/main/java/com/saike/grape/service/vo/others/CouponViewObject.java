package com.saike.grape.service.vo.others;

import com.saike.grape.service.vo.basic.BaseViewObject;

/**
 * 保养券信息视图类
 */
public class CouponViewObject extends BaseViewObject<CouponViewObject> {

    private static final long serialVersionUID = 8259446563223594392L;
    
    private String name;                    // 保养券名称
    private String fitToMinKm;              // 适用最小公里数
    private String fitToMaxKm;              // 适用最大公里数
    private String fitToMinDaysUsed;        // 适用最小使用年限（天数）
    private String fitToMaxDaysUsed;        // 适用最小使用年限（天数）
    private String fitToEmissionVolume;     // 适用排量范围
    private String fitToVehicleScope;       // 适用车辆范围
    private String fitToCity;               // 适用城市编号
    private String beginDatetime = "";      // 起始日期
    private String endDatetime = "";        // 结束日期
    private String moneyAmount;             // 金额
    private String color1;                  // 色值1
    private String color2;                  // 色值2
    private String ruleBrief;               // 规则简介
    private String ruleDetail;              // 规则详情,text格式自动转换
    
    private String description;              // 描述
    private String states;                   // 保养券状态（启用 '1" 停止 "0"）
    private String validInDays;              // 有效期天数
    private String updatedDatetime = "";     //更新日期
    private String actived; 	             //保养券状态（启用 '1" 停止 "0"）
    
    public String getValidInDays() {
		return validInDays;
	}
    
	public void setValidInDays(String validInDays) {
		this.validInDays = validInDays;
	}
	
	public  String isActived() {
        return actived;
    }
	
    public  void setActived(String actived) {
        this.actived = actived;
        if(states==null||"".equals(states)){
            this.states=actived;
        }
    }

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}


    public String getStates() {
        if(states==null||"".equals(states)){
            states= actived;
        }
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFitToMinKm() {
        return fitToMinKm;
    }

    public void setFitToMinKm(String fitToMinKm) {
        this.fitToMinKm = fitToMinKm;
    }

    public String getFitToMaxKm() {
        return fitToMaxKm;
    }

    public void setFitToMaxKm(String fitToMaxKm) {
        this.fitToMaxKm = fitToMaxKm;
    }

    public String getFitToMinDaysUsed() {
        return fitToMinDaysUsed;
    }

    public void setFitToMinDaysUsed(String fitToMinDaysUsed) {
        this.fitToMinDaysUsed = fitToMinDaysUsed;
    }

    public String getFitToMaxDaysUsed() {
        return fitToMaxDaysUsed;
    }

    public void setFitToMaxDaysUsed(String fitToMaxDaysUsed) {
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

    public String getBeginDatetime() {
        return beginDatetime;
    }

    public void setBeginDatetime(String beginDatetime) {
        this.beginDatetime = beginDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
