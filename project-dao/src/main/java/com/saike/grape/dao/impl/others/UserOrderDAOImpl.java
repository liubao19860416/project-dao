package com.saike.grape.dao.impl.others;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.UserOrderDAO;
import com.saike.grape.dao.entity.others.UserOrder;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

/**
 * 用户订单实现DAO
 */
@Repository
public class UserOrderDAOImpl extends
        GenericDAOBatisImpl<UserOrder, UserOrderDAOImpl> implements
        UserOrderDAO {

    @Override
    public UserOrder getUserOrderDetailByCode(UserOrder order) {
        return this.selectOne("getUserOrderDetailByCode", order);
    }

    @Override
    public List<UserOrder> getUserOrderByStatus(Map<String, Object> map,
            Integer pageIndex, Integer pageSize) {
        if (pageIndex == -1 || pageSize == -1) {
            return selectList("getUserOrderByStatus", map);
        }
        return selectList("getUserOrderByStatus", map, pageIndex, pageSize);
    }

    @Override
    public List<UserOrder> getUserOrderListByCodes(List<String> codes) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("codes", codes);
        return this.selectList("getOrderByCodes", map);
    }
    
    @Override
    public int cancelUserOrder(UserOrder order) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int updateUserOrderStatus(UserOrder order) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", order.getStatus().toString());
        map.put("code", order.getCode());
        map.put("dealerRemark", order.getDealerRemark());
        map.put("statusIndex", order.getStatusIndex());
        return this.update("updateUserOrderStatus", map);
    }
    
    @Override
    public int batchUpdateUserOrderStatus(List<String> codes, OrderStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.getKey());
        map.put("codes", codes);
        map.put("statusIndex", status.getSort());
        return this.update("batchUpdateUserOrderStatus", map);
    }
    
    @Override
    public int updateCouponCodeByUserOrderCode(String orderCode, String couponCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", orderCode);
        map.put("couponCode", couponCode);
        return this.update("updateCouponCodeByUserOrderCode", map);
    }
    
    @Override
    public List<UserOrder> getUserOrderListByUserId(String userId, Integer pageIndex,Integer pageSize) {
        return selectList("getUserOrderListByUserId", userId, pageIndex, pageSize);
    }

    @Override
    public List<UserOrder> getUserOrderListDOP(Map<String, Object> map,Integer pageIndex, Integer pageSize) {
        if (pageIndex == -1 || pageSize == -1) {
            return selectList("getUserOrderListDOP", map);
        }
        return selectList("getUserOrderListDOP", map, pageIndex, pageSize);
    }

    @Override
    public List<UserOrder> getUserOrderListForCsc(Map<String, Object> params) {
        if (params.get("pageIndex") != null
                && (int) params.get("pageIndex") > 0) {
            int pageSize = (int) params.get("rowCount");
            return this.selectList("getUserOrderListForCsc", params,
                    (int) params.get("pageIndex"), pageSize);
        } else {
            return this.selectList("getUserOrderListForCsc", params);
        }
    }
    
    @Override
    public boolean getCouponIsExistByUserOrder(Map<String, Object> map) {
        return selectCount("getCouponIsExistByUserOrder", map) > 0 ? true : false;
    }

    @Override
    public Map<String, Long> getUserOrderStatistics(Map<String, Object> params) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("finishCount",
                getOrderCountCsc("getStatusEq9OrderCount", params));
        map.put("judgeCount",
                getOrderCountCsc("getStatusEq11OrderCount", params));
        map.put("receiveCount",
                getOrderCountCsc("getStatusEq2OrderCount", params));
        map.put("refuseCount",
                getOrderCountCsc("getStatusEq3OrderCount", params));
        map.put("remarkCount",
                getOrderCountCsc("getDealerRemarkOrderCount", params));
        map.put("totalCount", getOrderCountCsc("getOrderTotalCount", params));
        map.put("reverseCount", 0l);
        return map;
    }
    //为上面的统计方法服务
    private long getOrderCountCsc(String nSp, Map<String, Object> params) {
        return this.selectCount(nSp, params);
    }

}
