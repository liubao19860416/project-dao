package com.saike.grape.dao.impl.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meidusa.fastjson.JSON;
import com.saike.grape.dao.api.others.CityDAO;
import com.saike.grape.dao.entity.others.City;
import com.saike.grape.dao.entity.others.TreeNode;
import com.saike.grape.dao.utils.DAOUtils;

/**
 * 城市DAO实现类
 */

@Repository
public class CityDAOImpl extends TreeNodeDAOImpl<City, CityDAOImpl> implements
        CityDAO {

    private static final Logger logger = LoggerFactory.getLogger(CityDAOImpl.class);

    @Override
    public List<City> getProvinceList() {
        List<TreeNode> listNodes = super.getTreeNodeList();
        List<City> listCitys = new ArrayList<City>();
        for (TreeNode treeNode : listNodes) {
            City city = new City();
            try {
                DAOUtils.copyProperties(city, treeNode);
                listCitys.add(city);
            } catch (Exception e) {
                logger.error("City.getProvinceList of type conversion exception", e);
            }
        }
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(listCitys));
        return listCitys;
    }

    @Override
    public List<City> getCityList() {
        Map<String, Object> params = new HashMap<>();
        if(params!=null){
            params.put(VAR_TABLE_NAME, this.getTableName());
            logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params));
        }
        return selectList("getCityList", params);
    }

    @Override
    public List<City> getCityList(String provincePath) {
        List<TreeNode> listNodes = super.getTreeBranchList(provincePath);
        List<City> listCitys = new ArrayList<City>();
        for (TreeNode treeNode : listNodes) {
            City city = new City();
            try {
                BeanUtilsBean.getInstance().copyProperties(city, treeNode);
                listCitys.add(city);
            } catch (Exception e) {
                logger.error("City.getCityList of type conversion exception",e);
            }
        }
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(listCitys));
        return listCitys;
    }

    @Override
    public List<City> getAreaList(String cityPath) {
        List<TreeNode> listNodes = super.getTreeLeafList(cityPath);
        List<City> listCitys = new ArrayList<City>();
        for (TreeNode treeNode : listNodes) {
            City city = new City();
            try {
                BeanUtilsBean.getInstance().copyProperties(city, treeNode);
                listCitys.add(city);
                logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(city));
            } catch (Exception e) {
                logger.error("City.getAreaList of type conversion exception",e);
            }
        }
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(listCitys));
        return listCitys;
    }

    @Override
    public List<City> getCityByCodeList(List<String> code) {
        List<TreeNode> listNodes = super.getTreeNodeByCodeList(code);
        List<City> listCitys = new ArrayList<City>();
        for (TreeNode treeNode : listNodes) {
            City city = new City();
            try {
                BeanUtilsBean.getInstance().copyProperties(city, treeNode);
                listCitys.add(city);
            } catch (Exception e) {
                logger.error("City.getCityByCodeList of type conversion exception",e);
            }
        }
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(listCitys));
        return listCitys;
    }

    @Override
    public City getCityByName(String name) {
        Map<String, Object> params = new HashMap<>();
        if(params!=null){
            params.put(VAR_TABLE_NAME, this.getTableName());
            params.put("name", name);
        }
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(params));
        return selectOne("getCityByName", params);
    }

    @Override
    public int updateCity(City city) {
        logger.debug(this.getClass().getName()+"===>>"+ JSON.toJSONString(city));
        return update("updateCity", city);
    }

}
