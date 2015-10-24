package com.saike.grape.dao.impl.others;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.others.UserOrderHistoryDAO;
import com.saike.grape.dao.entity.others.UserOrderHistory;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;
import com.saike.grape.dao.utils.DAOUtils;

public class UserOrderHistoryDAOImplTest extends GenericDAOTest {
	@Autowired
	private UserOrderHistoryDAO userOrderHistoryDAO;
	
	@Test
	public void testInsert() {
		UserOrderHistory his = new UserOrderHistory();
		his.setCode(DAOUtils.uuid());
		his.setMaintenanceMoney(123);
		his.setOperatorCode("123");
		his.setOrderMoney(23);
		his.setStatus(OrderStatus.CANCELED);
		his.setUserOrderCode("111");
		userOrderHistoryDAO.insert(his);
	}

	@Test
	public void testGetOrderTrace() {
		UserOrderHistory his = new UserOrderHistory();
		his.setUserOrderCode("111");
		List<UserOrderHistory> list = userOrderHistoryDAO.getUserOrderTraceHistory(his);
		System.out.println(list.get(0).getStatus());
		System.out.println(list.size());
	}

}
