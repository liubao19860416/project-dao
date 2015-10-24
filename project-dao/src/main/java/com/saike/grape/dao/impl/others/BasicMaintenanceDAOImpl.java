package com.saike.grape.dao.impl.others;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.BasicMaintenanceDAO;
import com.saike.grape.dao.entity.others.Maintenance;

/**
 * 基础保养项目DAO处理类
 * 
 */
@Repository
public class BasicMaintenanceDAOImpl extends
        MaintenanceDAOImpl<Maintenance, BasicMaintenanceDAOImpl> implements
        BasicMaintenanceDAO {

    @Override
    protected String getMaintenanceTableNamePrefix() {
        return "basic";
    }

    /**
     * 查询各车型对应的换机油和换机油滤芯，用来和v1.1兼容
     * 
     * @param vehicleCode
     *            车弄code
     * @return 该车型对应的换机油和机油滤芯的基础项目的集合
     */
    public List<Maintenance> getMaintenancesByVehicleCode(String vehicleCode) {
        Map<String, String> map = new HashMap<>();
        map.put("vehicleCode", vehicleCode);
        return this.getSqlSession().selectList("getMaintenancesByVehicleCode",
                map);
    }

    /**
     * 根据id来查询该 项目或者产品的所有信息
     * 
     * @param id
     *            保养项目所对应的id
     * @return 保养项目对应的视图对象
     */
    public List<Maintenance> getMaintenanceById(String id) {
        return this.getMaintenance(id);
    }

    /**
     * 根据id来查询该 项目或者产品的所有信息
     * 
     * @param code
     *            保养项目所对应的code
     * @return 保养项目对应的视图对象
     */
    @Override
    public List<Maintenance> getMaintenanceByCode(String code) {
        return super.getMaintenanceByMaintCode(code);
    }

}
