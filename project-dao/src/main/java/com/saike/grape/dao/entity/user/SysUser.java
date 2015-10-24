package com.saike.grape.dao.entity.user;


import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

import com.saike.grape.dao.entity.basic.BaseEntity;
/**
 * 系统用户信息表
 */
@Alias("sysUser")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = -8192811753363162531L;
    
    private int userId;                 //Primary Key
    private String account;             //用户帐号
    private String password;            //密码
    private String userName;            //用户名
    private String appId;               //应用Id
    private int roleId;                 //角色ID
    private String appName;             //应用名字
    private String appVersion;          //版本号
    private String realName;            //真实姓名
    private String gender;              //性别
    private String phoneNo;             //手机号
//    private String deviceNo;            //终端设备号
//    private String status;              //用户状态
//    private String securityCode;        //手机验证码
//    private String accountType;         //账号类型 1：用户名2：手机 3：邮箱
//    private String userType;            //注册来源的应用ID 1电商主站应用，默认传0
//    private String createType;          //注册来源：1：网站2：手机3:手机快速注册4:保养管家
//    private String securityType;        //用户密码安全级别  1：高2：中3：低
//    private String authURL;             //验证激活URL  ，用于邮箱注册，手机 和用户名注册不用填写
//    private String plateformType;       //平台类型ios or andrion
    
//    public String getPlateformType() {
//        return plateformType;
//    }
//    
//    public void setPlateformType(String plateformType) {
//        this.plateformType = plateformType;
//    }
//    
//    public String getAccountType() {
//        return accountType;
//    }
//    
//    public void setAccountType(String accountType) {
//        this.accountType = accountType;
//    }
//    
//    public String getUserType() {
//        return userType;
//    }
//    
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }
//    
//    public String getCreateType() {
//        return createType;
//    }
//    
//    public void setCreateType(String createType) {
//        this.createType = createType;
//    }
//    
//    public String getSecurityType() {
//        return securityType;
//    }
//    
//    public void setSecurityType(String securityType) {
//        this.securityType = securityType;
//    }
//    
//    public String getAuthURL() {
//        return authURL;
//    }
//    
//    public void setAuthURL(String authURL) {
//        this.authURL = authURL;
//    }
    
    public String getAppVersion() {
        return appVersion;
    }
    
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return StringUtils.isEmpty(userName)?"":userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getRealName() {
        return StringUtils.isEmpty(realName)?"":realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getPhoneNo() {
        return StringUtils.isEmpty(phoneNo)?"":phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getPassword() {
        return StringUtils.isEmpty(password)?"":password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getGender() {
        return StringUtils.isEmpty(gender)?"":gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAccount() {
        return StringUtils.isEmpty(account)?"":account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public SysUser() {
        super();
    }

    public SysUser(int userId, String account, String password,
            String userName, String appId, int roleId, String appName,
            String appVersion, String realName, String gender, String phoneNo) {
        super();
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.appId = appId;
        this.roleId = roleId;
        this.appName = appName;
        this.appVersion = appVersion;
        this.realName = realName;
        this.gender = gender;
        this.phoneNo = phoneNo;
    }
    
//    public String getDeviceNo() {
//        return StringUtils.isEmpty(deviceNo)?"":deviceNo;
//    }
//    
//    public void setDeviceNo(String deviceNo) {
//        this.deviceNo = deviceNo;
//    }
//    
//    public String getStatus() {
//        return StringUtils.isEmpty(status)?"":status;
//    }
//    
//    public void setStatus(String status) {
//        this.status = status;
//    }
//    
//    
//    public String getSecurityCode() {
//        return securityCode;
//    }
//    
//    public void setSecurityCode(String securityCode) {
//        this.securityCode = securityCode;
//    }
    
    
}
