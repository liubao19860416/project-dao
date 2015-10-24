package com.saike.grape.dao.impl.others;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meidusa.fastjson.JSON;
import com.saike.grape.dao.api.others.UserCouponDAO;
import com.saike.grape.dao.api.others.UserOrderHistoryDAO;
import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

/**
 * 用户拥有的优惠券信息DAO实现类
 */
@Repository
public class UserCouponDAOImpl extends GenericDAOBatisImpl<UserCoupon, UserCouponDAOImpl> 
             implements UserCouponDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(UserOrderHistoryDAO.class);
    
    @Override
    public List<UserCoupon> selectUserCouponsByConditions(
            Map<String, Object> params, Integer pageIndex, Integer pageSize) {
        if(params!=null){
            // 自动获取需要操作的数据库表名
            params.put(VAR_TABLE_NAME, this.getTableName());
            logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params));
        }
        return this.selectList("selectUserCouponsByConditions", params,
                pageIndex, pageSize);
    }

    @Override
    public Long selectUserCouponsByConditionsCount(Map<String, Object> params) {
        if(params!=null){
            // 自动获取需要操作的数据库表名
            params.put(VAR_TABLE_NAME, this.getTableName());
            logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params));
        }
        return this.selectCount("selectUserCouponsByConditionsCount", params);
    }

    @Override
    public UserCoupon selectUserCouponsByOrderCode(String orderCode) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(orderCode));
        return this.selectOne("selectUserCouponsByOrderCode", orderCode);
    }

    @Override
    public List<UserCoupon> selectUserCouponsByOrderCodeList(
            List<String> orderCodes, Integer pageIndex, Integer pageSize) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(orderCodes)+",pageIndex:"+pageIndex+",pageSize:"+pageSize);
        return this.selectList("selectUserCouponsByOrderCodeList", orderCodes,
                pageIndex, pageSize);
    }

    @Override
    public Long selectUserCouponsByOrderCodeListCount(List<String> orderCodes) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(orderCodes));
        return this.selectCount("selectUserCouponsByOrderCodeListCount", orderCodes);
    }

    @Override
    public Long insertReturnId(UserCoupon userCoupon) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(userCoupon));
        return Long.parseLong(this.insert("insertReturnId", userCoupon)+"");
    }

    @Override
    public UserCoupon selectUserCouponById(long id) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(id));
        return this.selectOne("selectUserCouponById", id);
    }

}
