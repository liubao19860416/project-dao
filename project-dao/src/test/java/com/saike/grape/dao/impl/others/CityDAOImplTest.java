package com.saike.grape.dao.impl.others;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.saike.grape.dao.api.others.CityDAO;
import com.saike.grape.dao.entity.others.City;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAODatetimeUtils;
/**
 * 城市相关操作DAO测试类
 */
public class CityDAOImplTest extends GenericDAOTest{

    @Resource
    CityDAO cityDao;
    
    @Test
    public void getCityListTest() {
        List<City> cityList = cityDao.getCityList();
        assertNotNull( cityList );
    }
    
    @Test
    public void getCityByNameTest() {
        City city = cityDao.getCityByName("上海市");
        assertNotNull( city );
    }
    
    @Test
    public void insertCityTest(){
        City city = new City();
        city.setCode("99999");
        city.setName("测试城市");
        city.setLevel(5);
        city.setParentCode("null");
        city.setFullPath("null");
        city.setDescription("测试城市");
        city.setActived(true);
        city.setDeleted(false);
        city.setCreatedDatetime(
                DAODatetimeUtils.parseTimestamp( "2014-09-05 10:40:20" ));
        city.setUpdatedDatetime(
                DAODatetimeUtils.parseTimestamp( "2014-09-05 13:40:20" ));
        
        int result = cityDao.insert(city);
        assertTrue( 1 == result);
    }
    
    @Test
    public void getCityByCodeTest(){
        List<String> code = new ArrayList<>();
        code.add("310100");
        code.add("320500");
        code.add("510100");
        List<City> list = cityDao.getCityByCodeList(code);
        assertNotNull( list );
    }
    
    @Test
    public void updateCityTest(){
        City city = new City();
        city.setCode("99999");
        city.setName("城市");
        city.setLevel(5);
        city.setParentCode("null");
        city.setFullPath("null");
        city.setDescription("城市");
        city.setUpdatedDatetime(
                DAODatetimeUtils.parseTimestamp( "2014-09-05 14:40:20" ));
        int result = cityDao.updateCity(city);
        assertTrue( 1 == result);
    }

}
