package com.saike.grape.service.utils;

import java.awt.geom.Point2D;

import org.geotoolkit.referencing.GeodeticCalculator;

/**
 * 地理信息相关工具类<br>
 */
public class ServiceGeographyUtils {
    
    /**
     * 功能描述: 计算两点之间的距离(米) <br>
     *
     * @param center 中心点
     * @param turnPoint 非中心点的其它经纬度
     * @return the double 其它经纬度离中心点的距离（米）
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static double gpsDistance( double jingdu1,double weidu1,double jingdu2,double weidu2) {
        GeodeticCalculator calc = new GeodeticCalculator();
        
        Point2D c = new Point2D.Double(jingdu1, weidu1);
        Point2D t = new Point2D.Double(jingdu2, weidu2);
        calc.setStartingGeographicPoint(c);
        calc.setDestinationGeographicPoint(t);

        return calc.getOrthodromicDistance();
    }
}
