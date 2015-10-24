package com.saike.grape.service.api.others;

import java.util.List;

import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.service.genertic.GenericService;
import com.saike.grape.service.vo.others.CouponViewObject;

/**
 * 保养券信息服务层接口
 */
public interface CouponService extends GenericService<CouponViewObject, Coupon> {

    /**
     * 获取所有的优惠券信息列表
     * 
     * @return List<CouponViewObject>
     */
    public List<CouponViewObject> getAllCoupons();

    /**
     * 创建一个保养券
     * 
     * @param couponViewObject
     * @return 插入记录条数
     */
    public int createCoupon(CouponViewObject couponViewObject);

    /**
     * 根据code更新对应的保养券状态
     * 
     * @param couponViewObject
     * @return
     */
    public int updateCouponStatus(CouponViewObject couponViewObject);

    /**
     * 根据条件查询，获取Coupon列表
     * 
     * @param couponViewObject
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<CouponViewObject> getCouponList(
            CouponViewObject couponViewObject, int pageIndex, int pageSize);

    /**
     * 根据条件查询，获取Coupon列表
     * 
     * @param couponViewObject
     * @return
     */
    public List<CouponViewObject> getCouponList(CouponViewObject couponViewObject);
    
    /**
     * 查询当前最新创建的保养券的状态是否开启
     * @return
     */
    public String getCouponFlag();
    
    /**
     * 查询当前最新创建的保养券信息
     * @return 
     */
    public CouponViewObject getLatestedCoupon();

}
