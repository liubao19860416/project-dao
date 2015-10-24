package com.saike.grape.dao.datas.transfer.others;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;

@Repository
public class BasicMaintenanceTransfer extends AbstractTransfer {
    
    private static final String NS = 
            "datas.transfer.BasicMaintenanceTransferMapper.";

    @Override
    public void transfer( Connection conn ) {
        
        Map<String, Object> params = newParamsMap();
        
        // 基础保养项目
        getSqlSession().insert( NS + "insertBasicMaintenances", params );
        
        // 基础保养项目组
        getSqlSession().insert( NS + "insertBasicSparePartGroups", params );
        
        // 基础保养项目组包含的配件
        getSqlSession().insert( NS + "insertBasicSparePartGroupeds", params );
        
    }

}
