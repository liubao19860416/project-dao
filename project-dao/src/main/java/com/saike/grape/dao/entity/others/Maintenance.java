package com.saike.grape.dao.entity.others;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.FitToEntity;
import com.saike.grape.dao.utils.DAOConstants.MaintenancePeriodUnit;

/**
 * 保养项目实体类
 *
 */
@Alias( "maintenance" )
public class Maintenance extends FitToEntity {

    private static final long serialVersionUID = -679110879790651714L;

    private String name;                        // 保养项目名称
    private String maintenanceOwnerCode;        // 保养项目所有者编号
    private int periodValue;                    // 保养周期值
    private MaintenancePeriodUnit periodUnit;   // 保养周期单位
    private float laborHour;                    // 工时
    private String docURL;                      // 文案URL
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMaintenanceOwnerCode() {
        return maintenanceOwnerCode;
    }

    public void setMaintenanceOwnerCode(String maintenanceOwnerCode) {
        this.maintenanceOwnerCode = maintenanceOwnerCode;
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
    
    public float getLaborHour() {
        return laborHour;
    }
    
    public void setLaborHour(float laborHour) {
        this.laborHour = laborHour;
    }

    

    public String getDocURL() {
        return docURL;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }
    
}
