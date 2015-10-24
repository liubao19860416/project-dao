package com.saike.grape.dao.utils.typehandler;

import com.saike.grape.dao.utils.DAOConstants.MaintenancePeriodUnit;

/**
 * Constants.MaintenancePeriodUnit枚举类型转换类
 */
public class MaintenancePeriodUnitEnumTypeHandler
        extends StringValueEnumTypeHandler<MaintenancePeriodUnit> {

    
    public MaintenancePeriodUnitEnumTypeHandler() {
        super( MaintenancePeriodUnit.class );
    }
}
