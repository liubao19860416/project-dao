package com.saike.grape.dao.impl.others;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meidusa.fastjson.JSON;
import com.saike.grape.dao.api.others.UserOrderHistoryDAO;
import com.saike.grape.dao.entity.others.UserOrderHistory;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;
/**
 * 订单历史信息操作
 *
 */
@Repository
public class UserOrderHistoryDAOImpl extends
        GenericDAOBatisImpl<UserOrderHistory, UserOrderHistoryDAOImpl>
        implements UserOrderHistoryDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserOrderHistoryDAO.class);
    
    /**
     * 获取该订单的各个阶段生成过程记录集合信息
     */
    @Override
    public List<UserOrderHistory> getUserOrderTraceHistory(UserOrderHistory his) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(his));
        return this.selectList("getOrderTrace", his);
    }

}
