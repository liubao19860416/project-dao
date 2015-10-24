package com.saike.grape.dao.entity.others;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;
@Alias("userOrder")
public class UserOrder extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
	private String userCode;                   // 用户编号
	private String userAddressCode;            // 用户地址编号
	private String dealerCode;                 // 经销商编号
	private String dealerName;                 // 经销商名称
	private String dealerShortName;				//经销商名称缩写
	private String dealerProductCode;          // 经销商商品编号
	private double totalMoney;                 // 订单总金额
	private double actualAmount;               // 订单实际金额
	private Timestamp maintenanceDatetime;     // 保养日期
	private Timestamp beginDatetime;           // 订单开始时间
	private Timestamp endDatetime;             // 订单结束时间
	private String couponCode;                 // 保养券编号
	private OrderStatus status;                // 订单状态
	private String deviceId;                   // 手机硬件编号
	private String maintenanceTypeCode;  	   //保养类型code
	private String maintenanceTypeName;		   // 保养类型名称
	private String userVehicleCode;            // 用户车辆编号
	private String userVehicleRecordCode;      // 用户车辆记录编号
	private String userMobilePhone;            // 用户手机号
	private String userRemark;                 // 用户备注
	private String dealerRemark;               // 经销商备注
	private String source;                     // 软件下载来源
	private double dealerJingDu;               // 经销商经度
	private double dealerWeiDu;                // 经销商纬度
	private int userVehicleKmTraveled;       // 用户车辆行驶公里数
	private float laborHourDiscount;           // 工时折扣
	private float sparePartDiscount;           // 配件折扣
	private String userName;		  			//用户名称
	private String vehiclePlateNumber;		  //车牌号
	
	private String dealerPhone;					//经销商电话	
	private String dealerAddress;				//经销商地址
	
	private String serviceLevel;				//服务等级
	
	private int statusIndex;                    //状态排序

	private List<UserOrderBasicMaintenance> 
	               basicMaintenanceList
	                 = new ArrayList<UserOrderBasicMaintenance>() ;  // 基础产品列表
	
	public int getStatusIndex() {
        return statusIndex;
    }

    public void setStatusIndex(int statusIndex) {
        this.statusIndex = statusIndex;
    }

    public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getDealerPhone() {
		return dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDealerShortName() {
		return dealerShortName;
	}

	public void setDealerShortName(String dealerShortName) {
		this.dealerShortName = dealerShortName;
	}

	public double getDealerJingDu() {
		return dealerJingDu;
	}

	public void setDealerJingDu(double dealerJingDu) {
		this.dealerJingDu = dealerJingDu;
	}

	public double getDealerWeiDu() {
		return dealerWeiDu;
	}

	public void setDealerWeiDu(double dealerWeiDu) {
		this.dealerWeiDu = dealerWeiDu;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserAddressCode() {
		return userAddressCode;
	}

	public void setUserAddressCode(String userAddressCode) {
		this.userAddressCode = userAddressCode;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerProductCode() {
		return dealerProductCode;
	}

	public void setDealerProductCode(String dealerProductCode) {
		this.dealerProductCode = dealerProductCode;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Timestamp getMaintenanceDatetime() {
		return maintenanceDatetime;
	}

	public void setMaintenanceDatetime(Timestamp maintenanceDatetime) {
		this.maintenanceDatetime = maintenanceDatetime;
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

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMaintenanceTypeCode() {
		return maintenanceTypeCode;
	}

	public void setMaintenanceTypeCode(String maintenanceTypeCode) {
		this.maintenanceTypeCode = maintenanceTypeCode;
	}

	public String getMaintenanceTypeName() {
		return maintenanceTypeName;
	}

	public void setMaintenanceTypeName(String maintenanceTypeName) {
		this.maintenanceTypeName = maintenanceTypeName;
	}

	public String getUserVehicleCode() {
		return userVehicleCode;
	}

	public void setUserVehicleCode(String userVehicleCode) {
		this.userVehicleCode = userVehicleCode;
	}

	public String getUserVehicleRecordCode() {
		return userVehicleRecordCode;
	}

	public void setUserVehicleRecordCode(String userVehicleRecordCode) {
		this.userVehicleRecordCode = userVehicleRecordCode;
	}

	public String getUserMobilePhone() {
		return userMobilePhone;
	}

	public void setUserMobilePhone(String userMobilePhone) {
		this.userMobilePhone = userMobilePhone;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getDealerRemark() {
		return dealerRemark;
	}

	public void setDealerRemark(String dealerRemark) {
		this.dealerRemark = dealerRemark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getUserVehicleKmTraveled() {
        return userVehicleKmTraveled;
    }

    public void setUserVehicleKmTraveled(int userVehicleKmTraveled) {
        this.userVehicleKmTraveled = userVehicleKmTraveled;
    }

    public float getLaborHourDiscount() {
		return laborHourDiscount;
	}

	public void setLaborHourDiscount(float laborHourDiscount) {
		this.laborHourDiscount = laborHourDiscount;
	}

	public float getSparePartDiscount() {
		return sparePartDiscount;
	}

	public void setSparePartDiscount(float sparePartDiscount) {
		this.sparePartDiscount = sparePartDiscount;
	}
	


	public List<UserOrderBasicMaintenance> getBasicMaintenanceList() {
		return basicMaintenanceList;
	}

	public void setBasicMaintenanceList(
	        List<UserOrderBasicMaintenance> basicMaintenanceList) {
		this.basicMaintenanceList = basicMaintenanceList;
	}

	public String getVehiclePlateNumber() {
		return vehiclePlateNumber;
	}

	public void setVehiclePlateNumber(String vehiclePlateNumber) {
		this.vehiclePlateNumber = vehiclePlateNumber;
	}
	
}
