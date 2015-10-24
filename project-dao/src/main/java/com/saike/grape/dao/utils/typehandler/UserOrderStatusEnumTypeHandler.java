package com.saike.grape.dao.utils.typehandler;

import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

/**
 * 用户订单状态枚举类
 */
public class UserOrderStatusEnumTypeHandler 
        extends StringValueEnumTypeHandler<OrderStatus> {

    public UserOrderStatusEnumTypeHandler() {
        
        super( OrderStatus.class );
    }

}
