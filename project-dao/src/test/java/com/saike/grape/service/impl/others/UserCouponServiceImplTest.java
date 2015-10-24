package com.saike.grape.service.impl.others;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.utils.DAOUtils;
import com.saike.grape.service.api.others.UserCouponService;
import com.saike.grape.service.genertic.ServiceBasicTest;
import com.saike.grape.service.vo.others.UserCouponViewObject;

/**
 * 用户保养券信息服务实现类的junit单元测试
 */
public class UserCouponServiceImplTest extends ServiceBasicTest {

    @Autowired
    private UserCouponService userCouponService;

    @Test
    public void testGetUserCouponsByConditions() {
        Map<String, Object> params=new HashMap<String, Object>();
        // params.put("code", "fd5ee89275df405aa4015bf985719814");
        // params.put("couponCode", "323");
        params.put("userCode", "260462");
        params.put("orderCode", "201407281300000578");
        // params.put("status", "2");
        // params.put("userMobilePhone", "15821067376");
        // params.put("actived", "1");
        List<UserCouponViewObject> list = userCouponService.getUserCouponsByConditions(params, 0, 0);
        
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        
        if(list!=null&&list.size()!=0) {
            UserCouponViewObject userCouponViewObject = list.get(0);
            Assert.assertNotNull(userCouponViewObject);
            Assert.assertEquals("282977", userCouponViewObject.getUserCode());
        }
    }
    
    @Test
    public void testGetAllUserCoupons() {
        List<UserCouponViewObject> list = userCouponService.getAllUserCoupons();
        if(list!=null&&list.size()!=0) {
            UserCouponViewObject userCouponViewObject = list.get(0);
            Assert.assertNotNull(userCouponViewObject);
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(9, list.size());
    }
    
    @Test
    public void testGetUserCouponByOrderCode() throws Exception {
        UserCouponViewObject couponViewObject = userCouponService.getUserCouponByOrderCode("201407281300000591");
        
        Assert.assertNotNull(couponViewObject);
        
        Assert.assertEquals("201407281300000591",couponViewObject.getOrderCode());
    }

    @Test
    public void testGetAllUserCouponsByUserCode() {
        String userCode = "282977";
        Integer pageIndex = 1;
        Integer pageSize = 1000;
        List<UserCouponViewObject> list = userCouponService
                .getUserCouponsByUserCode(userCode, pageIndex, pageSize);
        
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        UserCouponViewObject userCouponViewObject = list.get(0);
        Assert.assertNotNull(userCouponViewObject);
    }
    
    /**
     * 测试保养券生成service方法
     * @throws Exception
     */
    @Test
    public void testGenerateCouponForGrape() throws Exception {
        
        UserCouponViewObject viewObject=new UserCouponViewObject();
        viewObject.setCouponCode("323");
        viewObject.setOrderCode("201407281300000591");
        viewObject.setUserCode("282977");
        viewObject.setUserMobilePhone("15821067376");
        viewObject.setUserVehiclePlateNumber("苏F987nv");
        viewObject.setVerifyCode("F987nv");
        viewObject.setStatus("2");
        viewObject.setCode(DAOUtils.uuid());
        
        int result = userCouponService.generateUserCouponForGrape(viewObject);
        
        Assert.assertEquals(1, result);
    }
    
}
