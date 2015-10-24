package com.saike.grape.dao.datas.transfer.others;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.UserOrderHistoryDAO;
import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;
import com.saike.grape.dao.entity.others.UserOrderHistory;
import com.saike.grape.dao.utils.DAOConstants.OrderStatus;
import com.saike.grape.dao.utils.DAODatetimeUtils;

/**
 * 订单历史数据导入
 */

@Repository
public class UserOrderHistoryTransfer extends AbstractTransfer {

    private static final Logger logger = 
            LoggerFactory.getLogger( UserOrderHistoryTransfer.class );
    
    @Autowired
    UserOrderHistoryDAO userOrderHistoryDAO;
    
    @Override
    public void transfer( Connection srcConn ) {
        
        Statement st = null;
        ResultSet rs = null;
        
        try {
            List<UserOrderHistory> userOrderHistorys = new ArrayList<UserOrderHistory>();
            
            st = srcConn.createStatement();
            String sql = "select * from t_order_his";
            rs = st.executeQuery( sql );
            
            while( rs.next() ){
                userOrderHistorys.add( createUserOrderHistory( rs ) );
            }
            
            userOrderHistoryDAO.insert( userOrderHistorys );
            userOrderHistorys.clear();
            
        }catch( Exception ex ) {
            logger.error( "DealerTransfer.transfer exception: ", ex );
            throw new RuntimeException( ex );
        }finally {
            closeStatement( st );
            closeResultSet( rs );
        }
        
    }
    
    private UserOrderHistory createUserOrderHistory(ResultSet rs)
            throws SQLException {

        Timestamp currentDatetime = DAODatetimeUtils.currentTimestamp();

        UserOrderHistory sp = new UserOrderHistory();

        sp.setCode( rs.getString("id") );
        sp.setUserOrderCode( rs.getString("order_no") );
        sp.setStatus( OrderStatus.getKey(Integer.parseInt(rs.getString("order_status"))));
        sp.setOperatorCode( rs.getString("operater_no") );
        sp.setOrderMoney( rs.getDouble("order_amt") );
        sp.setMaintenanceMoney( rs.getDouble("maint_amt") );
        sp.setDescription( null );
        sp.setActived( true );
        sp.setDeleted( false );
        sp.setCreatedDatetime( rs.getTimestamp( "create_time" ) );
        sp.setUpdatedDatetime( currentDatetime );

        return sp;
    }

}
