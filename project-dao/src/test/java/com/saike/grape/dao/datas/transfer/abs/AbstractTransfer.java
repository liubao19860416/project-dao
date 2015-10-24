package com.saike.grape.dao.datas.transfer.abs;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.utils.DAODatetimeUtils;

public abstract class AbstractTransfer 
    extends SqlSessionDaoSupport
    implements Transfer {

    private static final Logger logger = 
            LoggerFactory.getLogger( AbstractTransfer.class );
    
    public static final String SH_CITY_CODE = "310100";
    
    protected static final Timestamp createdDatetime = 
            DAODatetimeUtils.currentTimestamp();
    
    protected static final Timestamp updatedDatetime = createdDatetime;
    
    private static final Map<String, String> mapVelSeriesName =
            new HashMap<>();
            
    private static final Map<String, String> mapVelSeriesCodes =
            new HashMap<>();
            
    private static final Map<String, String> mapVelMaintName =
            new HashMap<>();
    
    private static final Map<String, String> mapEmissionVolume =
            new HashMap<>();
    
    private static final Map<String, String> mapMaintenanceCode = 
            new HashMap<>();
            
    static {
/**
select vs.id as series_id, vm.id as velmodel_id, mp.id as proj_id, mp.name as proj_name, vs.`name`
from t_velseries vs inner join t_maint_velmodel vm on vs.id=vm.velseries_id
inner join t_maint_subproj ms on ms.maint_velmode_id=vm.id
inner join t_maint_proj mp on ms.maint_proj_id=mp.id
where vs.id=1
*/
        mapVelSeriesName.put( "1", "RW350" );
        mapVelSeriesName.put( "2", "RW550" );
        mapVelSeriesName.put( "3", "RWW5" );
        mapVelSeriesName.put( "4", "RW750" );
        mapVelSeriesName.put( "5", "RW950" );
        
        mapVelSeriesCodes.put( "1", "RW-350" );
        mapVelSeriesCodes.put( "2", "RW-550" );
        mapVelSeriesCodes.put( "3", "RW-W5-" );
        mapVelSeriesCodes.put( "4", "RW-750" );
        mapVelSeriesCodes.put( "5", "RW-950" );
        
        mapVelMaintName.put( "1", "换机油" );
        mapVelMaintName.put( "2", "更换机油滤芯" );
        mapVelMaintName.put( "3", "更换空气滤芯" );
        mapVelMaintName.put( "4", "更换空调滤芯" );
        mapVelMaintName.put( "5", "更换燃油滤芯" );
        mapVelMaintName.put( "6", "更换转向助力液" );
        mapVelMaintName.put( "7", "更换火花塞" );
        mapVelMaintName.put( "8", "更换变速箱油" );
        mapVelMaintName.put( "9", "正时皮带涨紧器" );

        mapEmissionVolume.put( "1", "1.5L" );
        mapEmissionVolume.put( "2", "1.5T" );
        mapEmissionVolume.put( "3", "1.8L" );
        mapEmissionVolume.put( "4", "1.8T" );
        mapEmissionVolume.put( "5", "1.8T" );
        mapEmissionVolume.put( "6", "3.2L" );
        mapEmissionVolume.put( "7", "1.8T" );
        mapEmissionVolume.put( "8", "2.5L" );
        mapEmissionVolume.put( "9", "2.0L" );
        mapEmissionVolume.put( "10", "2.4L" );
        mapEmissionVolume.put( "11", "3.0L" );
        
        mapMaintenanceCode.put( "RW550正时皮带涨紧器1.8L", "RW550正时皮带涨紧器1.8L" );
        mapMaintenanceCode.put( "RW550正时皮带涨紧器1.8T", "RW550正时皮带涨紧器1.8T" );
        
        mapMaintenanceCode.put( "RWW5换机油1.8T", "RWW5换机油1.8T" );
        mapMaintenanceCode.put( "RWW5更换机油滤芯1.8T", "RWW5更换机油滤芯1.8T" );
        mapMaintenanceCode.put( "RWW5更换空气滤芯1.8T", "RWW5更换空气滤芯1.8T" );
        mapMaintenanceCode.put( "RWW5更换空调滤芯1.8T", "RWW5更换空调滤芯1.8T" );
        mapMaintenanceCode.put( "RWW5更换燃油滤芯1.8T", "RWW5更换燃油滤芯1.8T" );
        mapMaintenanceCode.put( "RWW5更换转向助力液1.8T", "RWW5更换转向助力液1.8T" );
        mapMaintenanceCode.put( "RWW5更换火花塞1.8T", "RWW5更换火花塞1.8T" );
        mapMaintenanceCode.put( "RWW5更换变速箱油1.8T", "RWW5更换变速箱油1.8T" );
        mapMaintenanceCode.put( "RWW5正时皮带涨紧器1.8T", "RWW5正时皮带涨紧器1.8T" );
        
        mapMaintenanceCode.put( "RWW5换机油3.2L", "RWW5换机油3.2L" );
        mapMaintenanceCode.put( "RWW5更换机油滤芯3.2L", "RWW5更换机油滤芯3.2L" );
        mapMaintenanceCode.put( "RWW5更换空气滤芯3.2L", "RWW5更换空气滤芯3.2L" );
        mapMaintenanceCode.put( "RWW5更换空调滤芯3.2L", "RWW5更换空调滤芯3.2L" );
        mapMaintenanceCode.put( "RWW5更换燃油滤芯3.2L", "RWW5更换燃油滤芯3.2L" );
        mapMaintenanceCode.put( "RWW5更换转向助力液3.2L", "RWW5更换转向助力液3.2L" );
        mapMaintenanceCode.put( "RWW5更换火花塞3.2L", "RWW5更换火花塞3.2L" );
        mapMaintenanceCode.put( "RWW5更换变速箱油3.2L", "RWW5更换变速箱油3.2L" );
        mapMaintenanceCode.put( "RWW5正时皮带涨紧器3.2L", "RWW5正时皮带涨紧器3.2L" );
        
        mapMaintenanceCode.put( "RW750换机油1.8T", "RW750换机油1.8T" );
        mapMaintenanceCode.put( "RW750换机油2.5L", "RW750换机油2.5L" );
        mapMaintenanceCode.put( "RW750正时皮带涨紧器1.8T", "RW750正时皮带涨紧器1.8T" );
        mapMaintenanceCode.put( "RW750正时皮带涨紧器2.5L", "RW750正时皮带涨紧器2.5L" );
        
        mapMaintenanceCode.put( "RW950换机油3.0L", "RW950换机油3.0L" );
        mapMaintenanceCode.put( "RW950更换机油滤芯3.0L", "RW950更换机油滤芯3.0L" );
        mapMaintenanceCode.put( "RW950更换火花塞2.0L", "RW950更换火花塞2.0L" );
        mapMaintenanceCode.put( "RW950更换火花塞2.4L", "RW950更换火花塞2.4L" );
        mapMaintenanceCode.put( "RW950更换火花塞3.0L", "RW950更换火花塞3.0L" );
        mapMaintenanceCode.put( "RW950正时皮带涨紧器3.0L", "RW950正时皮带涨紧器3.0L" );
        
    }
    
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    public AbstractTransfer() {
    }
    
    protected static String getBasicMaintenanceCode( int velSeriesId, 
            int maintVelModelId, int maintProjId ) {
        
        String brandCode = mapVelSeriesName.get( 
                String.valueOf( velSeriesId ) );
        
        if( brandCode == null || "".equals( brandCode ) ) {
            throw new IllegalArgumentException( 
                    "Cannot get brand code with {"
                    + "velSeriesId=" + velSeriesId
                    + "}" );
        }
        
        String emissionVolume = mapEmissionVolume.get( 
                String.valueOf( maintVelModelId ) );
        
        if( emissionVolume == null || "".equals( emissionVolume ) ) {
            throw new IllegalArgumentException( 
                    "Cannot get emission code with {"
                    + "maintVelModelId=" + maintVelModelId
                    + "}" );
        }
        
        String maintCode = mapVelMaintName.get( 
                String.valueOf( maintProjId ) );
        
        if( maintCode == null || "".equals( maintCode ) ) {
            throw new IllegalArgumentException( 
                    "Cannot get maintenance code with {"
                    + "maintProjId=" + maintProjId
                    + "}" );
        }
        
        String code = mapMaintenanceCode.get( 
                brandCode + maintCode + emissionVolume );
        
        return code != null ? code : brandCode + maintCode;
    }
    
    protected static String getVehicleCode( int velSeriesId, 
            int maintVelModelId ) {
        
        String brandCode = mapVelSeriesCodes.get( 
                String.valueOf( velSeriesId ) );
        
        if( brandCode == null || "".equals( brandCode ) ) {
            throw new IllegalArgumentException( 
                    "Cannot get brand code with {"
                    + "velSeriesId=" + velSeriesId
                    + "}" );
        }
        
        String emissionVolume = mapEmissionVolume.get( 
                String.valueOf( maintVelModelId ) );
        
        if( emissionVolume == null || "".equals( emissionVolume ) ) {
            throw new IllegalArgumentException( 
                    "Cannot get emission code with {"
                    + "maintVelModelId=" + maintVelModelId
                    + "}" );
        }
        
        return brandCode + "---" + emissionVolume.replace( ".", "" );
    }
    
    @PostConstruct
    public void init() { 
        super.setSqlSessionFactory( sqlSessionFactory );
    }

    protected Map<String, Object> newParamsMap() {
        Map<String, Object> params = new HashMap<>();
        params.put( "createdDatetime", createdDatetime );
        params.put( "updatedDatetime", updatedDatetime );
        return params;
    }
    
    public void postTransfer( Connection strConn ) {
        // do something after the datas been transferred completely
    }
    
    protected void closeStatement( Statement st ) {
        if( st != null ) {
            try {
                st.close();
            }catch( Exception ex ) {
                logger.error( "closeStatement exception: ", ex );
            }
        }
    }
    
    protected void closeResultSet( ResultSet rs ) {
        if( rs != null ) {
            try {
                rs.close();
            }catch( Exception ex ) {
                logger.error( "closeResultSet exception: ", ex );
            }
        }
    }
    
}
