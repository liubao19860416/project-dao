package com.saike.grape.dao.entity.user;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;

@Alias("userLogin")
public class UserLogin extends BaseEntity{
    private static final long serialVersionUID = 7933975322203942957L;
    
    private String errorCode;
    private UserLoginResult result;
    
    public String getErrorCode() {
    	return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
    	this.errorCode = errorCode;
    }
    
    public UserLoginResult getResult() {
    	return result;
    }
    
    public void setResult(UserLoginResult result) {
    	this.result = result;
    }
	
}
