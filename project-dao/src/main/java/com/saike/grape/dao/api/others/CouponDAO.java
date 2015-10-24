package com.saike.grape.dao.api.others;

import java.util.List;
import java.util.Map;

import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.generic.GenericDAO;

/**
 * 保养券（规则）的DAO接口
 */
public interface CouponDAO extends GenericDAO<Coupon> {
	
	/**
	 * 覆写的根据id查询，忽略active和deleted字段（父类中已经是final修饰了）
	 */
	public Coupon findByID(Long id);
	
	/**
	 * 自定义的插入对象中非空属性的方法
	 */
	public Long insertBySelective(Coupon coupon);

	/**
	 *  根据查询条件（查询vo中不为空的属性），进行查询保养券（规则）信息列表
	 * @return List<Coupon>
	 */
	public List<Coupon> selectAllCouponsByConditions(Map<String, Object> map,
	        Integer pageIndex, Integer pageSize);
	
	/**
	 *  根据查询条件，进行查询保养券（规则）信息列表数量
	 * @param coupone
	 * @return List<Coupon>
	 */
	public Long selectAllCouponsByConditionsCount(Map<String, Object> map);
	
    /**
    * 查询当前最新保养券是否是开启 状态
    */
    public String getCouponFlag();

    /**
     * 查询最新创建的保养券
     */
    public Coupon selectLatestedCoupon();


}