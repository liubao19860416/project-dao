package com.saike.grape.dao.api.others;

import java.util.List;

import com.saike.grape.dao.entity.others.City;

/**
 * 城市DAO接口
 */

public interface CityDAO extends TreeNodeDAO<City> {
    
    public List<City> getProvinceList();
    
    public List<City> getCityList();
    
    public List<City> getCityList( String provincePath );
    
    public List<City> getAreaList( String cityPath );
    
    public List<City> getCityByCodeList( List<String> code );
    
    public City getCityByName( String name );
    
    public int updateCity( City city );
    
}
