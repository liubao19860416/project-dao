package com.saike.grape.dao.api.others;


import java.util.List;

import com.saike.grape.dao.entity.others.Maintenance;

/**
 * 基础保养项目DAO接口
 */
public interface BasicMaintenanceDAO extends MaintenanceDAO<Maintenance> {

    /**
     * 查询各车型对应的换机油和换机油滤芯，用来和v1.1兼容
     * @param vehicleCode 车型code
     * @return 该车型对应的换机油和机油滤芯的基础项目的集合
     */
    List<Maintenance> getMaintenancesByVehicleCode(String vehicleCovde);

    /**
     * 根据id来查询该 项目或者产品的所有信息
     * @param id 保养项目所对应的id
     * @return 保养项目对应的视图对象
     */
    List<Maintenance> getMaintenanceById(String id);
    
}
