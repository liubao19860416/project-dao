package com.saike.grape.dao.impl.others;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.others.CouponDAO;
import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.DAOUtils;

/**
 * 保养券DAO实现的测试类
 */
public class CouponDAOImplTest extends GenericDAOTest {

	@Autowired
	private CouponDAO couponDAO;

	/**
	 * 查询所有的优惠券信息（本地实现）
	 */
	@Test
	public void testSelectAll() {
		// 测试查询所有的列表
		// List<Coupon> selectList = couponDAO.selectAll();
		// Assert.assertNotNull(selectList);
		// System.out.println(selectList.size());

		// 查询根据条件查询对用的信息列表
		int pageIndex = 1;
		int pageSize = 10;
		Coupon coupon = new Coupon();
		// coupon.setId(4l);
		coupon.setName("name");
		// coupon.setCode("2004");
		// coupon.setFitToBrand("dazhong");
		// coupon.setFitToCity("shanghai");
		coupon.setActived(false);
		// coupon.setDeleted(false);
		// coupon.setMoneyAmount(600f);
		// coupon.setBeginDatetime(new Timestamp(new Date().getTime()));
		// coupon.setEndDatetime(new Timestamp(new Date().getTime()));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coupon", coupon);
		// map.put("pageIndex", pageIndex);
		// map.put("pageSize", pageSize);

		List<Coupon> list = couponDAO.selectAllCouponsByConditions(map,
				pageIndex, pageSize);

		coupon = new Coupon();
		long count = couponDAO.selectAllCouponsByConditionsCount(map);
		//count = couponDAO.selectAllCouponsByConditionsCount(null);

		// Assert.assertEquals(5, list.size());
		// Assert.assertEquals(5, count);
		Assert.assertEquals(count, list.size());

		// System.out.println(list);
		// System.out.println(list.size());

	}

	/**
	 * 批量更新（待实现）
	 * 单条记录更新（本地实现）
	 */
	@Test
	public void testUpdate() {
		Coupon coupon = new Coupon();
		coupon.setId(3l);
		//coupon.setActived(false);
		//coupon.setDeleted(true);
		couponDAO.update(coupon);//默认的更新状态，设置active为true，deleted为false
		
		coupon.setId(4l);
		coupon.setName("name004");
		coupon.setCode("2004");
		coupon.setFitToVehicleScope("dazhong");
		coupon.setFitToCity("shanghai");
		coupon.setActived(true);
		coupon.setDeleted(false);
		coupon.setMoneyAmount(800f);

		// Coupon coupon2 = new Coupon();
		// coupon2.setId(3l);
		// coupon2.setName("name003");
		// coupon2.setCode("2003");
		// coupon2.setFitToVehicleScope("dazhong");
		// coupon2.setFitToCity("shanghai");
		// coupon2.setActived(true);
		// coupon2.setDeleted(false);
		// coupon2.setMoneyAmount(200f);

		// 根据id更新单条数据
		int update = couponDAO.update(coupon);
		Assert.assertEquals(1, update);

		// 批量更新数据
		// List<Coupon> entities = new ArrayList<Coupon>();
		// entities.add(coupon);
		// entities.add(coupon2);
		// couponDAO.update(entities);// 这里返回插入操作影响的记录条数；

	}

	/**
	 * 测试单个记录插入和批量插入
	 */
	@Test
	public void testInsertE() {
		// 不能为空的属性
		Coupon coupon = new Coupon();
		// coupon.setId(4l);
		coupon.setCode(DAOUtils.uuid());
		coupon.setName("name000");
		coupon.setFitToMinKm(1000);
		coupon.setFitToMaxKm(10000);
		coupon.setFitToMinDaysUsed(100);
		coupon.setFitToMaxDaysUsed(10000);
		coupon.setFitToEmissionVolume("3t");

		coupon.setFitToVehicleScope("DZ");
		coupon.setFitToCity("SZ,SH");
		coupon.setActived(true);
		coupon.setDeleted(false);
		coupon.setMoneyAmount(200f);
		coupon.setColor1("color1");
		coupon.setColor2("color2");

		Coupon coupon2 = new Coupon();
		// coupon2.setId(4l);
		coupon2.setCode(DAOUtils.uuid());
		coupon2.setName("name000");
		coupon2.setFitToMinKm(1000);
		coupon2.setFitToMaxKm(10000);
		coupon2.setFitToMinDaysUsed(100);
		coupon2.setFitToMaxDaysUsed(10000);
		coupon2.setFitToEmissionVolume("3t");

		coupon2.setFitToVehicleScope("DZ");
		coupon2.setFitToCity("shanghai");
		coupon2.setActived(true);
		coupon2.setDeleted(false);
		coupon2.setMoneyAmount(200f);
		coupon2.setColor1("color1");
		coupon2.setColor2("color2");

		// 可以为空的属性
		coupon.setCreatedDatetime(DAODatetimeUtils.currentTimestamp());
		coupon.setUpdatedDatetime(new Timestamp(new Date().getTime()));
		coupon.setBeginDatetime(DAODatetimeUtils.currentTimestamp());
		coupon.setEndDatetime(DAODatetimeUtils.afterOneWeek(DAODatetimeUtils
				.currentTimestamp()));
		coupon.setDescription("coupon");
		coupon.setRuleBrief("coupon");
		coupon.setRuleDetail("coupon");

		List<Coupon> entities = new ArrayList<Coupon>();

		// 批量插入数据
		entities.add(coupon);
		entities.add(coupon2);
		couponDAO.insert(entities);// 这里返回插入操作影响的记录条数；

		// 插入单条数据
		// couponDAO.insert(coupon);

		// System.out.println("Ok。。。");

	}

	/**
	 * 根据id进行单个逻辑删除和批量逻辑删除
	 */
	@Test
	public void testDeleteById() {

		List<Coupon> entities = new ArrayList<Coupon>();
		Coupon coupon1 = new Coupon();
		coupon1.setId(1l);

		Coupon coupon2 = new Coupon();
		coupon2.setId(2l);

		Coupon coupon3 = new Coupon();
		coupon3.setId(3l);

		entities.add(coupon1);
		entities.add(coupon2);
		entities.add(coupon3);

		// 删除单个记录
		couponDAO.delete(coupon1);

		// 批量删除记录（根据id进行删除）
		couponDAO.delete(entities);
	}

	/**
	 * 根据id进行查询
	 */
	@Test
	public void testGetCouponFlag() {
	    String flag = couponDAO.getCouponFlag();
	    Assert.assertNotNull(flag);
	    Assert.assertEquals("1", flag);
	    System.out.println(flag);
	}
	/**
	 * 根据id进行查询
	 */
	@Test
	public void testFindById() {
		Coupon coupon = couponDAO.findById(3l);
		Assert.assertNotNull(coupon);
		System.out.println(coupon);
	}

	/**
	 * 根据code查询对应的优惠券信息（优惠券是唯一标识的unique）
	 */
	@Test
	public void testFindByCode() {
		Coupon coupon = couponDAO
				.findByCode("323");
		Assert.assertNotNull(coupon);
		// System.out.println(coupon);

	}

	/**
	 * 根据code进行逻辑删除
	 */
	@Test
	public void testDeleteByCode() {
		couponDAO.deleteByCode("84dd4c5b66574c0f869da7f63e6b284d");
	}

}
