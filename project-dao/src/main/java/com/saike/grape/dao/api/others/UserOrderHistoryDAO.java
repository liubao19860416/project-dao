package com.saike.grape.dao.api.others;

import java.util.List;

import com.saike.grape.dao.entity.others.UserOrderHistory;
import com.saike.grape.dao.generic.GenericDAO;
/**
 * 用户订单历史记录表
 *
 */
public interface UserOrderHistoryDAO extends GenericDAO<UserOrderHistory> {
    
    /**
     * 获取该订单的各个阶段生成过程记录集合信息
     */
    public List<UserOrderHistory> getUserOrderTraceHistory(UserOrderHistory his);
}
