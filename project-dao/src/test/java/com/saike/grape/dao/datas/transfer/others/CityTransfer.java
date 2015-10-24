package com.saike.grape.dao.datas.transfer.others;

import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.CityDAO;
import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;
import com.saike.grape.dao.entity.others.City;
import com.saike.grape.dao.utils.DAOConstants;
import com.saike.grape.dao.utils.DAODatetimeUtils;

/**
 * 城市数据导入 OK
 */

@Repository
public class CityTransfer extends AbstractTransfer {
    
    @Autowired
    private CityDAO cityDAO;
    
    @SuppressWarnings("unchecked")
    @Override
    public void transfer(Connection conn) {
        SAXBuilder builder = new SAXBuilder();
        
        try {
            String path =System.getProperty("user.dir")+"\\src\\test\\resources\\datas-transfer-mapper\\regions.xml";
            Document doc = builder.build(new File(path));
            
            //取得根元素
            Element element = doc.getRootElement();
            //得到根元素的所有子元素
            List<Element> provElement = element.getChildren();
            
            List<City> cityList = new ArrayList<City>();
            for(int i = 0; i < provElement.size(); i++){
                Timestamp currentDatetime = DAODatetimeUtils.currentTimestamp();
                Element prov = provElement.get(i);
                
                City provCity = new City();
                provCity.setCode( prov.getAttributeValue("code") );
                provCity.setName( prov.getAttributeValue("cn") );
                provCity.setLevel( Integer.valueOf(prov.getAttributeValue("level")) );
                provCity.setParentCode( null );
                provCity.setFullPath( prov.getAttributeValue("code") );
                provCity.setDescription( null );
                provCity.setActived( false );
                provCity.setDeleted( false );
                provCity.setCreatedDatetime( currentDatetime );
                provCity.setUpdatedDatetime( currentDatetime );
                
                cityList.add( provCity );
                List<Element> cityElement = prov.getChildren();
                for(int j = 0; j < cityElement.size(); j++){
                    Element city = cityElement.get(j);
                    
                    City city1 = new City();
                    city1.setCode( city.getAttributeValue("code") );
                    city1.setName( city.getAttributeValue("cn") );
                    city1.setLevel( Integer.valueOf(city.getAttributeValue("level")) );
                    city1.setParentCode( prov.getAttributeValue("code") );
                    city1.setFullPath( prov.getAttributeValue("code")+DAOConstants.FULL_PAHT_CODE_SEPARATOR+city.getAttributeValue("code") );
                    city1.setDescription( null );
                    if("上海市".equals(city.getAttributeValue("cn")) 
                            || "苏州市".equals(city.getAttributeValue("cn")) 
                            || "成都市".equals(city.getAttributeValue("cn"))){
                        city1.setActived( true );
                    }else{
                        city1.setActived( false );
                    }
                    city1.setDeleted( false );
                    city1.setCreatedDatetime( currentDatetime );
                    city1.setUpdatedDatetime( currentDatetime );
                    
                    cityList.add( city1 );
                    List<Element> ctyElement = city.getChildren();
                    for(int k = 0; k < ctyElement.size(); k++){
                        Element cty = ctyElement.get(k);
                        
                        City cty1 = new City();
                        cty1.setCode( cty.getAttributeValue("code") );
                        cty1.setName( cty.getAttributeValue("cn") );
                        cty1.setLevel( Integer.valueOf(cty.getAttributeValue("level")) );
                        cty1.setParentCode( city.getAttributeValue("code") );
                        cty1.setFullPath( prov.getAttributeValue("code")+DAOConstants.FULL_PAHT_CODE_SEPARATOR+city.getAttributeValue("code")+DAOConstants.FULL_PAHT_CODE_SEPARATOR+cty.getAttributeValue("code") );
                        cty1.setDescription( null );
                        cty1.setActived( false );
                        cty1.setDeleted( false );
                        cty1.setCreatedDatetime( currentDatetime );
                        cty1.setUpdatedDatetime( currentDatetime );
                        
                        cityList.add( cty1 );
                    }
                }
            }
            cityDAO.insert( cityList );
            cityList.clear();
            
        } catch (Exception ex) {
            throw new RuntimeException( ex );
        } 
    }

}
