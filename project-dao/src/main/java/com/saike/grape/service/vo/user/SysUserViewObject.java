package com.saike.grape.service.vo.user;

import com.saike.grape.service.vo.basic.BaseViewObject;

/**
 * 保养券信息视图类
 */
public class SysUserViewObject extends BaseViewObject<SysUserViewObject> {

    private static final long serialVersionUID = 8259446563223594392L;
    
    private String states;                   // 保养券状态（启用 '1" 停止 "0"）
    private String description;              // 描述
    private String beginDatetime = "";      // 起始日期
    private String endDatetime = "";        // 结束日期
    private String updatedDatetime = "";     //更新日期
    
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStates() {
        return states;
    }
    public void setStates(String states) {
        this.states = states;
    }
    public String getUpdatedDatetime() {
        return updatedDatetime;
    }
    public void setUpdatedDatetime(String updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
    

}
