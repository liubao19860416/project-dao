package com.saike.grape.dao.entity.others;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

@Alias("orderHistory")
public class UserOrderHistory extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String userOrderCode;	    //订单编号
	private OrderStatus status;			//订单状态
	private String operatorCode;	    //操作人编号
	private double orderMoney;		    //订单金额	
	private double	maintenanceMoney;   //保养金额
	
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getUserOrderCode() {
		return userOrderCode;
	}
	public void setUserOrderCode(String userOrderCode) {
		this.userOrderCode = userOrderCode;
	}
	
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public double getMaintenanceMoney() {
		return maintenanceMoney;
	}
	public void setMaintenanceMoney(double maintenanceMoney) {
		this.maintenanceMoney = maintenanceMoney;
	}
}
