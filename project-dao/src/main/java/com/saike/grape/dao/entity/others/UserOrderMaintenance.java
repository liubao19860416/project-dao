package com.saike.grape.dao.entity.others;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;

@Alias("userOrderMaintenance")
public class UserOrderMaintenance extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private String userOrderCode;	 		//订单编号
	private String maintenanceCode;	 		//基础保养项目编号
	private String name;	  		 		//基础保养项目名称
	private float laborHour;		 		//工时	
	private float laborHourPrice;			//工时指导价
	private float laborHourDiscountPrice;	//工时优惠价
	private float totalMoney;	 			//项目价格
	private boolean hasDoc;				
	private float totalDiscountMoney;       //项目折后价
	  
	public float getTotalDiscountMoney() {
		 return totalDiscountMoney;
	 }

	public void setTotalDiscountMoney(float totalDiscountMoney) {
		this.totalDiscountMoney = totalDiscountMoney;
	}
	
	public String getUserOrderCode() {
		return userOrderCode;
	}
	public void setUserOrderCode(String userOrderCode) {
		this.userOrderCode = userOrderCode;
	}
	public String getMaintenanceCode() {
		return maintenanceCode;
	}
	public void setMaintenanceCode(String maintenanceCode) {
		this.maintenanceCode = maintenanceCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLaborHour() {
		return laborHour;
	}
	public void setLaborHour(float laborHour) {
		this.laborHour = laborHour;
	}
	public float getLaborHourPrice() {
		return laborHourPrice;
	}
	public void setLaborHourPrice(float laborHourPrice) {
		this.laborHourPrice = laborHourPrice;
	}
	public float getLaborHourDiscountPrice() {
		return laborHourDiscountPrice;
	}
	public void setLaborHourDiscountPrice(float laborHourDiscountPrice) {
		this.laborHourDiscountPrice = laborHourDiscountPrice;
	}
	public float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public boolean isHasDoc() {
		return hasDoc;
	}
	public void setHasDoc(boolean hasDoc) {
		this.hasDoc = hasDoc;
	}
	
}
