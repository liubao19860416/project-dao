package com.saike.grape.dao.api.others;

import java.util.List;
import java.util.Map;

import com.saike.grape.dao.entity.others.UserOrder;
import com.saike.grape.dao.generic.GenericDAO;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

/**
 * 用户订单相关操作DAO实现
 * 
 */
public interface UserOrderDAO extends GenericDAO<UserOrder> {

    /**
     * 获取订单详情
     */
    public UserOrder getUserOrderDetailByCode(UserOrder order);

    /**
     * 根据经销商获取订单分页列表
     */
    public List<UserOrder> getUserOrderByStatus(Map<String, Object> map,
            Integer pageIndex, Integer pageSize);
    
    /**
     * 根据codes集合来查询
     */
    public List<UserOrder> getUserOrderListByCodes(List<String> codes);
    
    /**
     * 更新订单状态
     */
    public int updateUserOrderStatus(UserOrder order);

    /**
     * 批量更新订单状态
     */
    public int batchUpdateUserOrderStatus(List<String> codes, OrderStatus status);
    
    /**
     * 根据orderCode，更新对应过的用户订单的couponCode字段
     */
    public int updateCouponCodeByUserOrderCode(String orderCode, String couponCode);
    
    /**
     * 根据用户获取订单分页列表
     */
    public List<UserOrder> getUserOrderListByUserId(String userId, Integer pageIndex,Integer pageSize);

    /**
     * DOP调用的订单列表查询接口
     */
    public List<UserOrder> getUserOrderListDOP(Map<String, Object> map,
            Integer pageIndex, Integer pageSize);
    /**
     * CSC调用的订单列表查询接口
     */
    public List<UserOrder> getUserOrderListForCsc(Map<String, Object> params);
    
    /**
     * 根据用户的订单号列表，查询对应的用户是否存在保养券
     */
    public boolean getCouponIsExistByUserOrder(Map<String, Object> map);

    /**
     * 根据条件查找订单的一些数据统计信息
     */
    public Map<String, Long> getUserOrderStatistics(Map<String, Object> params);
    
    /** 
     * 更新订单标记（取消订单）
     */
    public int cancelUserOrder(UserOrder order);

}
