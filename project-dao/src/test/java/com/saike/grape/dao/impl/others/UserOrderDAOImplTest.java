package com.saike.grape.dao.impl.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.others.UserOrderDAO;
import com.saike.grape.dao.entity.others.UserOrder;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

public class UserOrderDAOImplTest extends GenericDAOTest {
	@Autowired
	private UserOrderDAO userOrderDAO;
	int pageIndex=1;
	int pageSize=10;

	@Test
	public void testInsert() {
		UserOrder order = new UserOrder();
		order.setId(12345l);
		order.setCode("2233");
		order.setStatus(OrderStatus.CANCELED);
		order.setDealerCode("111");
		order.setUserCode("11");
		order.setDealerProductCode("111");
		order.setDealerName("abc");
		order.setVehiclePlateNumber("vehicleNumber");
		order.setUserVehicleCode("uservehiclecode");
		order.setMaintenanceTypeCode("mtc");
		order.setDealerJingDu(120);
		order.setDealerWeiDu(100);
		order.setUserMobilePhone("mobile");
		order.setMaintenanceTypeName("mtn");
		order.setUserVehicleRecordCode("vrc");
		userOrderDAO.insert(order);
	}
	@Test
	public void testGetOrderList() {
		String userId="282977";
		List<UserOrder> orderList = userOrderDAO.getUserOrderListByUserId(userId, pageIndex, pageSize);
		for(UserOrder uo:orderList){
			System.out.println(uo.getCode());
			System.out.println(uo.getActualAmount());
			System.out.println(uo.getCode());
			System.out.println(uo.getStatus());
			System.out.println(uo.getBasicMaintenanceList().size());
			
		}
	}

	@Test
	public void testGetOrderByStatus() {
		Map<String,Object> map = new HashMap<String,Object>();
	      map.put("status", Integer.valueOf("0"));
          map.put("dealerCode", 111);
          map.put("orderBy", "createTime");
          map.put("orderType", "DESC");
          userOrderDAO.getUserOrderByStatus(map, pageIndex, pageSize);
	}

	@Test
	public void testGetOrderDetail() {
		UserOrder order = new UserOrder();
		order.setCode("201407281300000629");
		UserOrder order1 = userOrderDAO.getUserOrderDetailByCode(order);
		System.out.println(order1.getBasicMaintenanceList().size());
	}

	@Test
	public void testUpdateOrderStatus() {
		System.out.println(OrderStatus.UNCONFIRMED.toString());
		UserOrder order = new UserOrder();
		order.setCode("201407281300000591");
		order.setStatus(OrderStatus.UNCONFIRMED);
		userOrderDAO.updateUserOrderStatus(order);
	}
	
	
	@Test
	public void testGetUserOrderByCodes(){
		List<String> ds = new ArrayList<String>();
		ds.add("201407281300000591");
		ds.add("201407281300000606");
		List<UserOrder> orders = userOrderDAO.getUserOrderListByCodes(ds);
		System.out.println(orders.size());
	}
	
	@Test
	public void testBatchUpdateOrderByCodes(){
		List<String> ds = new ArrayList<String>();
		ds.add("201407281300000591");
		ds.add("201407281300000606");
		int re = userOrderDAO.batchUpdateUserOrderStatus(ds, OrderStatus.CANCELED);
		System.out.println(re);
	}
	
	@Test
	public void testGetOrderListDOP(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", "20140728");
		map.put("userName", "li");
		map.put("userMobilePhone", "");
		map.put("dealerCode", "122");
		map.put("status", OrderStatus.CANCELED);
		map.put("beginPrice", 123);
		map.put("endPrice", 234);
		map.put("vehiclePlateNumber", "sx");
		map.put("startMinitTime", "2014-09-08 11:11:11");
		map.put("endMiniTime", "2014-09-08 11:11:11");
		map.put("startCommitTime", "2014-09-08 11:11:11");
		map.put("endCommitTime", "2014-09-08 11:11:11");
		List<UserOrder> orders  = userOrderDAO.getUserOrderListDOP(map,-1,-1);
		
		System.out.println(orders.size());
	}
	
	@Test
	public void testGetCouponByOrder(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deviceId", "");
		map.put("userCode", "333074");
		map.put("userMobilePhone", "");
		map.put("vehiclePlateNumber", "");
		System.out.println(userOrderDAO.getCouponIsExistByUserOrder(map));
	}

}
