package com.saike.grape.dao.datas.transfer.others;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.UserOrderDAO;
import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;
import com.saike.grape.dao.entity.others.UserOrder;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;

/**
 * 订单数据导入 
 */

@Repository
public class UserOrderTransfer extends AbstractTransfer {

    private static final Logger logger = 
            LoggerFactory.getLogger( UserOrderTransfer.class );
    
    @Autowired
    UserOrderDAO userOrderDAO;
    
    private int p=1;
    
    @Override
    public void transfer( Connection srcConn ) {
        
        Statement st = null;
        ResultSet rs = null;
        
        try {
            List<UserOrder> userOrderList = new ArrayList<UserOrder>();
            
            st = srcConn.createStatement();
            String sql = "SELECT DISTINCT del.order_no,del.user_id,del.create_time,"
                    + "del.update_time,tail.pick_vel_addr,del.dealer_id,"
                    + "tail.dealer_name,tail.ds_id,del.order_amt,tail.dealer_tel,tail.dealer_address,"
                    + "del.maint_amt,tail.maint_date,tail.start_time,tail.end_time,"
                    + "del.order_status,tail.device_id,tail.mdse_type_id,tail.vkt,"
                    + "tail.vlp,tail.order_utel,tail.order_uname,tail.user_remark,"
                    + "tail.dealer_remark,tail.source,tail.dealer_lon,tail.dealer_lat,"
                    + "tail.laborhour_ds,tail.parts_ds,tail.ser_level,del.maint_velmodel_id,"
                    + "model.velseries_id,type.id,type.NAME, coupon.coupon_id "
                    + "FROM t_order del INNER JOIN t_order_detail tail "
                    + "ON del.order_no = tail.order_no INNER JOIN t_mdse_type type "
                    + "ON type.id = tail.mdse_type_id INNER JOIN t_maint_velmodel model "
                    + "ON del.maint_velmodel_id = model.id LEFT JOIN "
                    + "t_coupon_info coupon ON coupon.order_no = del.order_no";
            rs = st.executeQuery( sql );
            
            while( rs.next() ){
                userOrderList.add( createUserOrder( rs ) );
                p++;
                if(p % 200 == 0) {
                    userOrderDAO.insert( userOrderList );
                    userOrderList.clear();
                }
            }
            
            userOrderDAO.insert( userOrderList );
            userOrderList.clear();
            
        }catch( Exception ex ) {
            logger.error( "DealerTransfer.transfer exception: ", ex );
            throw new RuntimeException( ex );
        }finally {
            closeStatement( st );
            closeResultSet( rs );
        }
        
    }
    
    private UserOrder createUserOrder(ResultSet rs)
            throws SQLException {

        UserOrder sp = new UserOrder();

        sp.setCode( rs.getString("order_no") );
        sp.setUserCode( rs.getString( "user_id" ) );
        sp.setUserAddressCode( rs.getString( "pick_vel_addr" ) );
        sp.setDealerCode( rs.getString( "dealer_id" ) );
        sp.setDealerName( rs.getString( "dealer_name" ) );
        sp.setDealerShortName( rs.getString( "dealer_name" ) );
        sp.setDealerProductCode( rs.getString( "ds_id" ) );
        sp.setTotalMoney( rs.getDouble( "order_amt" ) );
        sp.setActualAmount( rs.getDouble( "maint_amt" ) );
        sp.setDealerPhone( rs.getString( "dealer_tel" ) );
        sp.setDealerAddress( rs.getString( "dealer_address" ) );
        
        sp.setMaintenanceDatetime( rs.getTimestamp( "maint_date" ) );
        sp.setBeginDatetime( rs.getTimestamp( "start_time" ) );
        sp.setEndDatetime( rs.getTimestamp( "end_time" ) );
        sp.setCouponCode( rs.getString( "coupon_id" ) );
        sp.setStatus( OrderStatus.getKey(Integer.parseInt(rs.getString("order_status"))));
        sp.setStatusIndex(sp.getStatus().getSort());
        sp.setDeviceId( rs.getString( "device_id" ) );
        sp.setMaintenanceTypeCode( rs.getString( "id" ) );
        sp.setMaintenanceTypeName( rs.getString( "NAME" ) );
        sp.setUserVehicleRecordCode( rs.getString( "user_id" ) );
        sp.setUserVehicleKmTraveled( rs.getInt( "vkt" ) );
        
        String vehicleCode = getVehicleCode( rs.getInt( "velseries_id" ),
                rs.getInt( "maint_velmodel_id" ));
        
        sp.setUserVehicleCode( vehicleCode );
        sp.setVehiclePlateNumber( rs.getString( "vlp" ) );
        sp.setUserMobilePhone( rs.getString( "order_utel" ) );
        sp.setUserName( rs.getString( "order_uname" ) );
        sp.setUserRemark( rs.getString( "user_remark" ) );
        sp.setDealerRemark( rs.getString( "dealer_remark" ) );
        
        sp.setSource( rs.getString( "source" ) );
        sp.setDealerJingDu( rs.getDouble( "dealer_lon" ) );
        sp.setDealerWeiDu( rs.getDouble( "dealer_lat" ) );
        sp.setLaborHourDiscount( rs.getFloat( "laborhour_ds" ) );
        sp.setSparePartDiscount( rs.getFloat( "parts_ds" ) );
        sp.setServiceLevel( rs.getString( "ser_level" ) );
        
        sp.setDescription( null );
        sp.setActived( true );
        sp.setDeleted( false );
        sp.setCreatedDatetime( rs.getTimestamp( "create_time" ) );
        sp.setUpdatedDatetime( rs.getTimestamp( "update_time" ) );

        return sp;
    }

}
