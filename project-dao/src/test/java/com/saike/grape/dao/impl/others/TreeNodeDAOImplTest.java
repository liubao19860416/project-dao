package com.saike.grape.dao.impl.others;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.saike.grape.dao.api.others.CityDAO;
import com.saike.grape.dao.entity.others.City;
import com.saike.grape.dao.generic.GenericDAOTest;

public class TreeNodeDAOImplTest extends GenericDAOTest {

    @Resource
    CityDAO cityDao;

    @Test
    public void getBrandListTest() {

    }

    @Test
    public void getVelseriesListTest() {

    }

    @Test
    public void getVelmodelListTest() {
    }

    @Test
    public void getProvinceListTest() {
        List<City> cityList = cityDao.getProvinceList();
        assertNotNull(cityList);
    }

    @Test
    public void getCityListTest() {
        List<City> cityList = cityDao.getCityList("310000");
        assertNotNull(cityList);
        assertTrue(1 == cityList.size());
    }

    @Test
    public void getAreaListTest() {
        List<City> cityList = cityDao.getAreaList("310100");
        assertNotNull(cityList);
    }

}
