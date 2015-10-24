package com.saike.grape.service.impl.others;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.utils.DAOConstants;
import com.saike.grape.dao.utils.DAOUtils;
import com.saike.grape.service.api.others.CouponService;
import com.saike.grape.service.genertic.ServiceBasicTest;
import com.saike.grape.service.vo.others.CouponViewObject;

/**
 * 保养券CouponService服务层测试类
 */
public class CouponServiceImplTest extends ServiceBasicTest {

    @Autowired
    private CouponService couponService;

    @Test
    public void testGetAllCoupons() {
        List<CouponViewObject> list = couponService.getAllCoupons();

        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testGetById() {
        Long id = 6l;
        CouponViewObject couponViewObject = couponService.getById(id);

        Assert.assertNotNull(couponViewObject);
    }

    @Test
    public void testGetByCode() {
        String code = "323";
        CouponViewObject couponViewObject = couponService.getByCode(code);

        Assert.assertNotNull(couponViewObject);
        Assert.assertEquals(new Long(1), couponViewObject.getId());
    }

    @Test
    public void testSaveV() {
        CouponViewObject couponViewObject = new CouponViewObject();

        couponViewObject.setCode(DAOUtils.uuid());
        couponViewObject.setName("测试名字-最新");
        couponViewObject.setFitToMinKm(DAOConstants.KM_MIN + "");
        couponViewObject.setFitToMaxKm(DAOConstants.KM_MAX + "");
        couponViewObject.setFitToMinDaysUsed(DAOConstants.DAYS_MIN + "");
        couponViewObject.setFitToMaxDaysUsed(DAOConstants.DAYS_MAX + "");
        couponViewObject.setFitToEmissionVolume(DAOConstants.FIT_TO_ALL);

        couponViewObject.setFitToVehicleScope(DAOConstants.FIT_TO_ALL);
        couponViewObject.setFitToCity(DAOConstants.FIT_TO_ALL);
        // couponViewObject.setActived(true+"");
        // couponViewObject.setDeleted(false+"");
        couponViewObject.setMoneyAmount(200 + "");
        couponViewObject.setColor1(DAOConstants.COLOR1_DEFAULT);
        couponViewObject.setColor2(DAOConstants.COLOR2_DEFAULT);

        int result = couponService.createCoupon(couponViewObject);

        Assert.assertEquals(1, result);

    }

    @Test
    public void testSaveListOfV() {
        CouponViewObject couponViewObject = new CouponViewObject();

        couponViewObject.setCode(DAOUtils.uuid());
        couponViewObject.setName("姓名1");
        couponViewObject.setFitToMinKm(DAOConstants.KM_MIN + "");
        couponViewObject.setFitToMaxKm(DAOConstants.KM_MAX + "");
        couponViewObject.setFitToMinDaysUsed(DAOConstants.DAYS_MIN + "");
        couponViewObject.setFitToMaxDaysUsed(DAOConstants.DAYS_MAX + "");
        couponViewObject.setFitToEmissionVolume(DAOConstants.FIT_TO_ALL);

        couponViewObject.setFitToVehicleScope(DAOConstants.FIT_TO_ALL);
        couponViewObject.setFitToCity(DAOConstants.FIT_TO_ALL);
        // couponViewObject.setActived(true+"");
        // couponViewObject.setDeleted(false+"");
        couponViewObject.setMoneyAmount(200 + "");
        couponViewObject.setColor1(DAOConstants.COLOR1_DEFAULT);
        couponViewObject.setColor2(DAOConstants.COLOR2_DEFAULT);

        CouponViewObject couponViewObject2 = new CouponViewObject();

        couponViewObject2.setCode(DAOUtils.uuid());
        couponViewObject2.setName("姓名2");
        couponViewObject2.setFitToMinKm(DAOConstants.KM_MIN + "");
        couponViewObject2.setFitToMaxKm(DAOConstants.KM_MAX + "");
        couponViewObject2.setFitToMinDaysUsed(DAOConstants.DAYS_MIN + "");
        couponViewObject2.setFitToMaxDaysUsed(DAOConstants.DAYS_MAX + "");
        couponViewObject2.setFitToEmissionVolume(DAOConstants.FIT_TO_ALL);

        couponViewObject2.setFitToVehicleScope(DAOConstants.FIT_TO_ALL);
        couponViewObject2.setFitToCity(DAOConstants.FIT_TO_ALL);
        // couponViewObject2.setActived(true+"");
        // couponViewObject2.setDeleted(false+"");
        couponViewObject2.setMoneyAmount(200 + "");
        couponViewObject2.setColor1(DAOConstants.COLOR1_DEFAULT);
        couponViewObject2.setColor2(DAOConstants.COLOR2_DEFAULT);

        ArrayList<CouponViewObject> list = new ArrayList<CouponViewObject>();
        list.add(couponViewObject);
        list.add(couponViewObject2);

        // couponService.save(list);//该方法更新后，不能实现，需要DAO覆写后才可以

        System.out.println("OK...");

    }

    @Test
    public void testDeleteById() {
        Long id = 1l;
        boolean actual = couponService.deleteById(id);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void testDeleteByCode() {
        String code = "2004";
        boolean actual = couponService.deleteByCode(code);
        Assert.assertEquals(true, actual);
    }

}
