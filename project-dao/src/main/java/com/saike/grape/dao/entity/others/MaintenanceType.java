package com.saike.grape.dao.entity.others;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.FitToEntity;
import com.saike.grape.dao.utils.DAOConstants.MaintenancePeriodUnit;

/**
 * 保养类型实体类
 *
 */
@Alias( "maintenanceType" )
public class MaintenanceType extends FitToEntity {

    private static final long serialVersionUID = 5456272354327728488L;

    private String name1;                       // 保养名称1
    private String name2;                       // 保养名称2
    private int periodValue;                    // 保养周期值
    private MaintenancePeriodUnit periodUnit;   // 保养周期单位
    
    /* 
     * 当从数据库读取保养类型时会设置此值来表示当前的具体保养类型
     * 该值为Constants.MAINTENANCE_TYPE_XXX
     * Note: 数据库中并没有该值对应字段
     */
    private int maintenanceTypeValue;
    
    public String getName1() {
        return name1;
    }
    
    public void setName1(String name1) {
        this.name1 = name1;
    }
    
    public String getName2() {
        return name2;
    }
    
    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    public int getPeriodValue() {
        return periodValue;
    }
    
    public void setPeriodValue(int periodValue) {
        this.periodValue = periodValue;
    }
    
    public MaintenancePeriodUnit getPeriodUnit() {
        return periodUnit;
    }
    
    public void setPeriodUnit(MaintenancePeriodUnit periodUnit) {
        this.periodUnit = periodUnit;
    }

    public int getMaintenanceTypeValue() {
        return maintenanceTypeValue;
    }

    public void setMaintenanceTypeValue(int maintenanceTypeValue) {
        this.maintenanceTypeValue = maintenanceTypeValue;
    }
    
    
}
