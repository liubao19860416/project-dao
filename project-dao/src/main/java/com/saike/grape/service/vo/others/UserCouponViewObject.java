package com.saike.grape.service.vo.others;

import com.saike.grape.service.vo.basic.BaseViewObject;

/**
 * 用户当前拥有的保养券信息视图类
 */
public class UserCouponViewObject extends BaseViewObject<UserCouponViewObject>{

	private static final long serialVersionUID = 790706798040216989L;

	private String userCode;                   // 用户编号
	private String couponCode;                 // 保养券编号
	private String userMobilePhone;            // 用户手机号
	private String orderCode;                  // 订单号
	private String status;                     // 保养券状态
	private String userVehiclePlateNumber;     // 用户车牌号码
	private String description="";             // 描述
    private String createdDatetime = "";       // 记录创建日期
    private String updatedDatetime = "";       // 记录最近修改日期
    private String promotionCode;              // 外部接口生成的id
    
    private String verifyCode;                 // 保养券验证编号
    //保养券显示的基本信息
    private String couponType;                  // 保养券名称
    private String amount;                      // 金额
    private String colorValue1;                 // 色值1
    private String colorValue2;                 // 色值2
    private String summary;                     // 规则简介
    private String detailDesc;                  // 规则详情
    private String startDate = "";              // 起始日期
    private String endDate = "";                // 结束日期
    
    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getColorValue1() {
        return colorValue1;
    }

    public void setColorValue1(String colorValue1) {
        this.colorValue1 = colorValue1;
    }

    public String getColorValue2() {
        return colorValue2;
    }

    public void setColorValue2(String colorValue2) {
        this.colorValue2 = colorValue2;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(String updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
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
