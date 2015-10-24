package com.saike.grape.dao.entity.others;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;

/**
 * 用户当前拥有的优惠券信息实体
 */
@Alias( "userCoupon" )
public class UserCoupon extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 790706798040216920L;

	private String userCode;                   // 用户编号
	private String couponCode;                 // 保养券编号
	private String verifyCode;                 // 保养券验证编号
	private String userMobilePhone;            // 用户手机号
	private String orderCode;                  // 订单号
	private String status;                     // 保养券状态--1已生成，2 已发放，3 已使用，4 已结算
	private String userVehiclePlateNumber;     // 用户车牌号码

	private String promotionCode;              // 外部接口生成的保养券号码
	
	private Coupon coupon;                     //用户保养券对应的保养券其他信息n->1
	
	public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getCouponCode() {
		return couponCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUserMobilePhone() {
		return userMobilePhone;
	}

	public void setUserMobilePhone(String userMobilePhone) {
		this.userMobilePhone = userMobilePhone;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserVehiclePlateNumber() {
		return userVehiclePlateNumber;
	}

	public void setUserVehiclePlateNumber(String userVehiclePlateNumber) {
		this.userVehiclePlateNumber = userVehiclePlateNumber;
	}

}