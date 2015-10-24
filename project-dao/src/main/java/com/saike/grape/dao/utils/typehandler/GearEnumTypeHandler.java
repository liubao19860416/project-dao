package com.saike.grape.dao.utils.typehandler;

import com.saike.grape.dao.utils.DAOConstants.Gear;


/**
 * 车挡枚举类型转换类
 */
public class GearEnumTypeHandler 
    extends StringValueEnumTypeHandler<Gear>  {

    public GearEnumTypeHandler() {
        super( Gear.class );
    }

}
