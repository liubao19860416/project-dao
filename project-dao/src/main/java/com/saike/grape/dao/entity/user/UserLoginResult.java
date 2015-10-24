package com.saike.grape.dao.entity.user;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;
/**
 * 用户登录返回信息实体类
 */
@Alias("userLoginResult")
public class UserLoginResult extends BaseEntity{

    private static final long serialVersionUID = -8597429120223891966L;
    
    private String mobilePhone;             //手机号
    private String updateTime;              //更新时间
    private String vkt;                     //公里数
    private Integer userId;                 //用户id
    private String gender;                  //性别
    private String pttUrl;                  //头像图片路径
    private String userName;                //用户名
    private String realName;                //用户真实姓名
    private String password;                //密码
    private String registerTime;            //注册时间
    private List<Object> pickOrRetAddr;     //送车和取车信息
    
    public List<Object> getPickOrRetAddr() {
    	return pickOrRetAddr;
    }
    
    public void setPickOrRetAddr(List<Object> pickOrRetAddr) {
    	this.pickOrRetAddr = pickOrRetAddr;
    }
    
    public String getMobilePhone() {
    	return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
    	this.mobilePhone = mobilePhone;
    }
    
    public String getUpdateTime() {
    	return updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
    	this.updateTime = updateTime;
    }
    
    public String getVkt() {
    	return vkt;
    }
    
    public void setVkt(String vkt) {
    	this.vkt = vkt;
    }
    
    public Integer getUserId() {
    	return userId;
    }
    
    public void setUserId(Integer userId) {
    	this.userId = userId;
    }
    
    
    public String getGender() {
    	return gender;
    }
    
    public void setGender(String gender) {
    	this.gender = gender;
    }
    
    public String getPttUrl() {
    	return pttUrl;
    }
    
    public void setPttUrl(String pttUrl) {
    	this.pttUrl = pttUrl;
    }
    
    public String getUserName() {
    	return userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public String getRealName() {
    	return realName;
    }
    
    public void setRealName(String realName) {
    	this.realName = realName;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getRegisterTime() {
    	return registerTime;
    }
    
    public void setRegisterTime(String registerTime) {
    	this.registerTime = registerTime;
    }

}
