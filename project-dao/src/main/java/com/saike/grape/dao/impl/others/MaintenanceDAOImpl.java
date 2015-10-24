package com.saike.grape.dao.impl.others;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.MaintenanceDAO;
import com.saike.grape.dao.entity.others.Maintenance;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

/**
 * 保养项目DAO处理抽象类
 */
@Repository
public abstract class MaintenanceDAOImpl<E extends Maintenance, T extends MaintenanceDAOImpl<E, T>>
        extends GenericDAOBatisImpl<E, T> implements MaintenanceDAO<E> {

    protected List<E> getAllMaintenances(String ownerCode) {
        if (StringUtils.isEmpty(ownerCode)) {
            throw new IllegalArgumentException("Argument dealer code is empty!");
        }
        return selectList(
                MaintenanceDAOImpl.class,
                "getAllMaintenances",
                newParams()
                        .put("ownerCode", ownerCode)
                        .put("t_maintenance",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_maintenance")
                        .put("t_spare_part_group",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_group")
                        .put("t_spare_part_grouped",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_grouped")
                        .put("t_spare_part",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part"));
    }

    protected List<E> getMaintenance(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Argument dealer code is empty!");
        }
        return selectList(
                MaintenanceDAOImpl.class,
                "getMaintenanceById",
                newParams()
                        .put("id", id)
                        .put("t_maintenance",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_maintenance")
                        .put("t_spare_part_group",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_group")
                        .put("t_spare_part_grouped",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_grouped")
                        .put("t_spare_part",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part"));
    }

    protected List<E> getMaintenanceByMaintCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("Argument dealer code is empty!");
        }
        return selectList(
                MaintenanceDAOImpl.class,
                "getMaintenanceById",
                newParams()
                        .put("code", code)
                        .put("t_maintenance",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_maintenance")
                        .put("t_spare_part_group",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_group")
                        .put("t_spare_part_grouped",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part_grouped")
                        .put("t_spare_part",
                                TABLE_NAME_PREFIX
                                        + getMaintenanceTableNamePrefix()
                                        + "_spare_part"));
    }

    protected abstract String getMaintenanceTableNamePrefix();

}
