package com.saike.grape.service.impl.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saike.grape.dao.api.others.CouponDAO;
import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.service.api.others.CouponService;
import com.saike.grape.service.genertic.GenericServiceImpl;
import com.saike.grape.service.vo.others.CouponViewObject;

/**
 * 保养券服务层接口实现类
 */
@Service
public class CouponServiceImpl extends
        GenericServiceImpl<CouponViewObject, Coupon, CouponServiceImpl>
        implements CouponService {
    
    private Log logger = LogFactory.getLog(CouponServiceImpl.class);
    
    @Autowired
    private CouponDAO couponDAO;

    // 对应的DAO使用构造方法的方式自动注入进来
    @Autowired
    public CouponServiceImpl(CouponDAO couponDAO) {
        super(couponDAO);
        this.couponDAO=couponDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CouponViewObject> getAllCoupons() {
        // 查询所有的保养券列表列表(当前查询最新的一条记即可)
        List<Coupon> list = couponDAO.selectAll();
        List<CouponViewObject> list2 = new ArrayList<>();
        
        //获取对应的列表总数
        if(list!=null&&list.size()>0){
	        for (Coupon coupon : list) {
	        	CouponViewObject transToViewObject =new CouponViewObject();
	        	transToViewObject=this.transToViewObject(coupon);
				if(coupon.isActived()){
					transToViewObject.setStates("1");
				}else{
					transToViewObject.setStates("0");
				}
				list2.add(transToViewObject);
			}
        }
        return list2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int createCoupon(CouponViewObject couponViewObject) {
        Coupon coupon = this.transToEntity(couponViewObject);
        coupon.setActived(true);//默认状态激活
        coupon.setDeleted(false);
        coupon.setCreatedDatetime(DAODatetimeUtils.currentTimestamp());
        coupon.setUpdatedDatetime(DAODatetimeUtils.currentTimestamp());
        //coupon.setCode(GrapeDAOUtils.uuid());
        return couponDAO.insert(coupon);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateCouponStatus(CouponViewObject couponViewObject) {
    	 Coupon coupon = couponDAO.findByID(Long.parseLong(couponViewObject.getCode()));
        //coupon.setActived(true);这里应该自动转换了
        if("1".equals(couponViewObject.getStates())){
            if(!coupon.isActived()){
                coupon.setActived(true);
            }
        }else{
            if(coupon.isActived()){
               coupon.setActived(false);
            }
        }
        return couponDAO.update(coupon);
    }

    
    /**
     * 获取保养券分页信息列表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CouponViewObject> getCouponList(
            CouponViewObject couponViewObject, int pageIndex, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        Coupon coupon = this.transToEntity(couponViewObject);
        map.put("coupon", coupon);
        return this.transToViewObject(couponDAO.selectAllCouponsByConditions(map, pageIndex, pageSize));
    }

    /**
     * 获取保养券分页信息列表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CouponViewObject> getCouponList(
            CouponViewObject couponViewObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        Coupon coupon = this.transToEntity(couponViewObject);
        map.put("coupon", coupon);
        // 修改位置
        return this.transToViewObject(couponDAO.selectAll());
        //return this.transToViewObject(couponDAO.selectAllCouponsByConditions(map,1,0));
    }

    /**
    * 获取保养券当前状态
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String getCouponFlag() {
        return couponDAO.getCouponFlag();
    }

    /**
     * 查询最新创建的保养券（新添加）
     */
    @Override
    public CouponViewObject getLatestedCoupon() {
        Coupon coupon= couponDAO.selectLatestedCoupon();
        return this.transToViewObject(coupon != null ? coupon: new Coupon());
    }
    

}
