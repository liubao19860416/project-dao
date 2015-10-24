package com.saike.grape.service.api.others;

import java.util.List;
import java.util.Map;

import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.service.genertic.GenericService;
import com.saike.grape.service.vo.others.UserCouponViewObject;

/**
 * 保养券信息服务层接口
 */
public interface UserCouponService extends
        GenericService<UserCouponViewObject, UserCoupon> {

    /**
     * 根据查询条件，获取对应的保养券分页信息列表
     * 
     * @return List<UserCouponViewObject>
     */
    public List<UserCouponViewObject> getUserCouponsByConditions(
            Map<String, Object> params, Integer pageIndex, Integer pageSize);
    
    /**
     * 获取所有用户的的保养券信息列表
     * 
     * @return List<UserCouponViewObject>
     */
    public List<UserCouponViewObject> getAllUserCoupons();

    /**
     * 根据userCode，获取指定用户的的保养券信息列表
     * 
     * @return List<UserCouponViewObject>
     */
    public List<UserCouponViewObject> getUserCouponsByUserCode(
            String userCode, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 生成用户保养券号信息的方法，替代直接的insert方法
     * 
     * @return int 返回插入的记录的id（暂时无用）
     */
    public int generateUserCouponForGrape(UserCouponViewObject viewObject);
    
    
    /**
     * 根据订单号查询对应的保养券信息
     * @param orderCode
     * @return
     */
    public UserCouponViewObject getUserCouponByOrderCode(String orderCode);

    /**
     * 根据OrderNos集合，查询对应的保养券信息列表
     * @param orderNos
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<UserCouponViewObject> getUserCouponListByOrderCodeList(
            List<String> orderCodes, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据OrderNos集合，查询对应的保养券信息列表总数
     * @param orderNos
     * @return
     */
    public Long getUserCouponListCountByOrderCodeList(
            List<String> orderCodes);

    /**
     * 根据id，获取对应的用户保养券信息
     * 
     * @param parseLong
     * @return
     */
    public UserCouponViewObject getUserCouponById(long id);
    

}
