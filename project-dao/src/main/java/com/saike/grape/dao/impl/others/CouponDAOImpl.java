package com.saike.grape.dao.impl.others;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meidusa.fastjson.JSON;
import com.saike.grape.dao.api.others.CouponDAO;
import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

/**
 * 保养券的DAO接口的实现类
 */
@Repository
public class CouponDAOImpl extends GenericDAOBatisImpl<Coupon, CouponDAOImpl>
		implements CouponDAO {

    private static final Logger logger = LoggerFactory.getLogger(CouponDAOImpl.class);
	
	@Override
	public Coupon findByID(Long id){
	    logger.debug(this.getClass().getName()+"===>>"+id);
		return this.selectOne("findByID", id);
	}

	@Override
	public List<Coupon> selectAllCouponsByConditions(Map<String, Object> params,
			Integer pageIndex, Integer pageSize) {
	    if(params!=null){
            params.put(VAR_TABLE_NAME, this.getTableName());
        }
	    logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params)+",pageIndex:"+pageIndex+",pageSize:"+pageSize);
		return this.selectList("selectAllCouponsByConditions",params,pageIndex,pageSize);
	}

	@Override
	public Long selectAllCouponsByConditionsCount(Map<String, Object> params) {
	    if(params!=null){
	        params.put(VAR_TABLE_NAME, this.getTableName());
	    }
	    logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params));
		return selectCount("selectAllCouponsByConditionsCount",params);
	}

	@Override
	public Long insertBySelective(Coupon coupon) {
	    logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(coupon));
		return Long.parseLong(this.insert("insertBySelective", coupon)+"");
	}
	
	/**
    * 获取保养券当前状态
    */
    @Override
    public String getCouponFlag() {
        logger.debug(this.getClass().getName()+"===>>.getCouponFlag()");
        return this.getSqlSession().selectOne("getCouponFlag");
    }

    /**
     * 获取当前最新创建的保养券相关信息
     */
    @Override
    public Coupon selectLatestedCoupon() {
        logger.debug(this.getClass().getName()+"===>>.selectLatestedCoupon()");
        return this.selectOne("selectLatestedCoupon");
    }


}
