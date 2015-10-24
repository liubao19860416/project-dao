package com.saike.grape.dao.impl.others;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.others.UserCouponDAO;
import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.DAOUtils;

/**
 * 用户的保养券DAO层的单元测试
 */
public class UserCouponDAOImplTest extends GenericDAOTest {

    @Autowired
    private UserCouponDAO userCouponDAO;

    /**
     * 测试更新操作,根据id进行操作（本地mapper实现）
     * 
     * 任何更新操作，都会是actived置为true，而deleted为false
     */
    @Test
    public void testUpdate() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(1l);
        userCoupon.setUserMobilePhone("13761603061");

        int update = userCouponDAO.update(userCoupon);
        Assert.assertEquals(1, update);

    }

    @SuppressWarnings("all")
    @Test
    public void testSelectUserCouponsByOrderCodeList() {
        List<String> orderCodes = new ArrayList<String>();
        orderCodes.add("111");
        orderCodes.add("222");
        orderCodes.add("333");
        List<UserCoupon> list =userCouponDAO
                .selectUserCouponsByOrderCodeList(orderCodes,1,100);

        Assert.assertNotNull(list);
        Assert.assertEquals(3, list.size());

    }
    
    @SuppressWarnings("all")
    @Test
    public void testSelectUserCouponsByOrderCode() {
        String orderCode="201407281300000591";
         UserCoupon userCoupon = userCouponDAO
                .selectUserCouponsByOrderCode(orderCode);
        
        Assert.assertNotNull(userCoupon);
        Assert.assertEquals(orderCode, userCoupon.getOrderCode());
        
    }


    /**
     * 测试条件查询，带分页参数的条件组合查询测试(默认的分页参数为0，10，查询的都是激活的有效的数据)
     * 
     * @throws Exception
     * @throws IllegalAccessException
     */
    @Test
    public void testSelectUserCouponsByConditions()
            throws IllegalAccessException, Exception {

        // 空参数查询结果列表
        Map<String, Object> params = new HashMap<String, Object>();
        // params.put("pageIndex", 1);
        // params.put("pageSize", 10);
        List<UserCoupon> list = userCouponDAO.selectUserCouponsByConditions(
                params, 1, 10);
        Assert.assertEquals(2, list.size());

        // 带查询条件和分页结果列表
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserMobilePhone("13761603061");
        params.put("userCoupon", userCoupon);
        list = userCouponDAO.selectUserCouponsByConditions(params, 1, 10);
        Assert.assertEquals(1, list.size());

        // 带分页查询结果列表，这里注意分页参数一定要为数字类型，而不是字符串“1”或者"3"
        params.put("pageIndex", 1);
        params.put("pageSize", 3);
        list = userCouponDAO.selectUserCouponsByConditions(params, 1, 10);
        Assert.assertEquals(3, list.size());
    }

    /**
     * 测试查询符合条件的列表信息总数量
     */
    @Test
    public void testSelectCount() {
        Map<String, Object> params = new HashMap<String, Object>();

        // 无查询条件
        long count = userCouponDAO
                .selectUserCouponsByConditionsCount(params);
        Assert.assertEquals(7, count);

        // 设置分页查询条件，默认为每页最大显示10条记录
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserMobilePhone("18611478781");

        params.put("userCoupon", userCoupon);
        count = userCouponDAO.selectUserCouponsByConditionsCount(params);
        Assert.assertEquals(6, count);

        System.out.println("OK...");
    }

    /**
     * 测试查询所有用户的保养券信息(本地方法)
     */
    @Test
    public void testSelectAll() {
        List<UserCoupon> list = userCouponDAO.selectAll();
        Assert.assertNotNull(list);

    }

    /**
     * 测试插入单条记录测试
     */
    @Test
    public void testInsertE() {
        UserCoupon userCoupon = new UserCoupon();

        // 必填参数
        userCoupon.setCode(DAOUtils.uuid());
        userCoupon.setUserCode(DAOUtils.uuid());
        userCoupon.setCouponCode(DAOUtils.uuid());
        userCoupon.setVerifyCode(DAOUtils.uuid());
        userCoupon.setUserMobilePhone("18611478781");
        userCoupon.setStatus("OK");
        userCoupon.setActived(true);
        userCoupon.setDeleted(false);

        // 选填参数
        userCoupon.setOrderCode("ok");
        userCoupon.setUserVehiclePlateNumber("ok");
        userCoupon.setUpdatedDatetime(DAODatetimeUtils.currentTimestamp());
        userCoupon.setCreatedDatetime(null);
        userCoupon.setDescription("ok");

        // 插入单条记录
        int insert = userCouponDAO.insert(userCoupon);

        Assert.assertEquals(1, insert);
    }

    /**
     * 插入批量记录测试
     */
    @Test
    public void testInsertListOfE() {
        UserCoupon userCoupon = new UserCoupon();
        // 必填参数
        userCoupon.setCode(DAOUtils.uuid());
        userCoupon.setUserCode(DAOUtils.uuid());
        userCoupon.setCouponCode(DAOUtils.uuid());
        userCoupon.setVerifyCode(DAOUtils.uuid());
        userCoupon.setUserMobilePhone("18611478781");
        userCoupon.setStatus("OK");
        userCoupon.setActived(true);
        userCoupon.setDeleted(false);

        UserCoupon userCoupon2 = new UserCoupon();
        // 必填参数
        userCoupon2.setCode(DAOUtils.uuid());
        userCoupon2.setUserCode(DAOUtils.uuid());
        userCoupon2.setCouponCode(DAOUtils.uuid());
        userCoupon2.setVerifyCode(DAOUtils.uuid());
        userCoupon2.setUserMobilePhone("18611478781");
        userCoupon2.setStatus("OK");
        userCoupon2.setActived(true);
        userCoupon2.setDeleted(false);

        List<UserCoupon> userCoupons = new ArrayList<UserCoupon>();
        userCoupons.add(userCoupon);
        userCoupons.add(userCoupon2);

        // 批量插入
        userCouponDAO.insert(userCoupons);

        System.out.println("OK...");
    }

    /**
     * 根据id进行查询
     */
    @Test
    public void testFindById() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(1l);
        UserCoupon userCoupon1 = userCouponDAO.findById(userCoupon.getId());
        userCoupon.setId(2l);
        UserCoupon userCoupon2 = userCouponDAO.findById(userCoupon.getId());
        Assert.assertNotNull(userCoupon1);
        Assert.assertNotNull(userCoupon2);
    }

    /**
     * 根据code进行查询
     * 
     * select * from t_user_coupon where code=? and actived=true and
     * deleted=false
     */
    @Test
    public void testFindByCode() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCode("ad089747d8d94b268bcd8123887bd6d6");
        UserCoupon userCoupon1 = userCouponDAO.findByCode(userCoupon.getCode());
        Assert.assertNotNull(userCoupon1);
        Assert.assertEquals(userCoupon1.getCode(),
                "ad089747d8d94b268bcd8123887bd6d6");
    }

    /**
     * 都是根据id进行逻辑删除操作的
     * 
     * update t_user_coupon set actived=false, deleted=true where id=?;
     */
    @Test
    public void testDeleteById() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(1l);
        int delete1 = userCouponDAO.delete(userCoupon);
        int delete2 = userCouponDAO.deleteById(userCoupon.getId());
        Assert.assertEquals(delete1, delete2);
        Assert.assertEquals(1, delete2);

    }

    /**
     * 根据code进行逻辑删除
     */
    @Test
    public void testDeleteByCode() {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCode("ad089747d8d94b268bcd8123887bd6d6");
        int deleteByCode = userCouponDAO.deleteByCode(userCoupon.getCode());

    }

    /**
     * 批量逻辑删除根据的还是id进行操作
     */
    @Test
    public void testDeleteListOfE() {
        UserCoupon userCoupon = new UserCoupon();
        // userCoupon.setCode("ad089747d8d94b268bcd8123887bd6d6");
        userCoupon.setId(1l);
        UserCoupon userCoupon2 = new UserCoupon();
        userCoupon2.setId(2l);
        List<UserCoupon> entities = new ArrayList<UserCoupon>();
        entities.add(userCoupon);
        entities.add(userCoupon2);

        userCouponDAO.delete(entities);
    }

    @Test
    public void testSelectOneString() {
        fail("Not yet implemented");
    }

}
