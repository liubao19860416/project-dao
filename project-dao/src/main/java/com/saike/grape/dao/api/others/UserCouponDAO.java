package com.saike.grape.dao.api.others;

import java.util.List;
import java.util.Map;

import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.dao.generic.GenericDAO;

/**
 * 用户拥有的优惠券信息DAO
 */
public interface UserCouponDAO extends GenericDAO<UserCoupon> {
    
    /**
     * 根据orderCode查询对应的UserCoupon
     */
    public UserCoupon selectUserCouponsByOrderCode(String orderCode);

    /**
     * 根据用户的订单号，获取保养劵分页集合 查询的是当前登录用户的所有订单下面的所有的保养券信息
     */
    public List<UserCoupon> selectUserCouponsByOrderCodeList(
            List<String> orderCodes,Integer pageIndex,Integer pageSize);
    
    /**
     * 根据OrderNos集合，查询对应的保养券信息列表总数
     */
    public Long selectUserCouponsByOrderCodeListCount(List<String> orderCodes);
    
    /**
     * 返回插入保养券的id
     */
    public Long insertReturnId(UserCoupon userCoupon);

    /**
     * 根据id获取对应的保养券相关信息
     * 
     * @param id
     * @return
     */
    public UserCoupon selectUserCouponById(long id);

    /**
     * 根据查询条件（查询map中保存的userCoupon对象不为空的属性），进行查询用户保养券信息列表
     * 
     * @param params
     * @return List<UserCoupon>
     */
    public List<UserCoupon> selectUserCouponsByConditions(
            Map<String, Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 根据查询条件（查询map中保存的userCoupon对象不为空的属性）进行查询用户保养券信息列表总数
     * 
     * @param params
     * @return List<UserCoupon>
     */
    public Long selectUserCouponsByConditionsCount(Map<String, Object> params);

    
}