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

import com.saike.grape.dao.api.others.UserCouponDAO;
import com.saike.grape.dao.api.others.UserOrderDAO;
import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;
import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.dao.utils.DAODatetimeUtils;

/**
 * 用户保养券UserCoupon数据导入
 */
@SuppressWarnings("all")
@Repository
public class UserCouponTransfer extends AbstractTransfer {

    private static final Logger logger = LoggerFactory
            .getLogger(UserCouponTransfer.class);

    @Autowired
    UserCouponDAO userCouponDAO;

    @Autowired
    UserOrderDAO userOrderDAO;

    private static final String NS = "datas.transfer.UserCouponTransferMapper.";

    @Override
    public void transfer(Connection srcConn) {

        // List<Coupon> list = couponDAO.selectAll();
        // Map<String, Object> params = newParamsMap();
        // super.getSqlSession().insert(NS + "insertCoupon", params);

        Statement st = null;
        ResultSet rs = null;
        Statement st2 = null;
        ResultSet rs2 = null;
        int i = 0;

        try {
            List<UserCoupon> userCouponList = new ArrayList<UserCoupon>();

            // 从旧的数据库表中读出我们需要的数据
            String sql = "select * from t_coupon_info";
            st = srcConn.createStatement();
            st2 = srcConn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                userCouponList.add(createUserCoupon(rs, st2, rs2));
                i++;
                if (i % 100 == 0) {
                    // 这里需要插入到新的数据库表中，采用默认的表名
                    userCouponDAO.insert(userCouponList);
                    Thread.sleep(1);
                    userCouponList.clear();
                }
            }

            // 将余数不足的记录插入到数据库表中
            if (userCouponList.size() != 0) {
                // 这里需要插入到新的数据库表中
                userCouponDAO.insert(userCouponList);
                Thread.sleep(1);
                userCouponList.clear();
            }
        } catch (Exception ex) {
            logger.error("UserCouponTransfer.transfer exception: ", ex);
            throw new RuntimeException( ex );
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeResultSet(rs2);
            closeStatement(st2);
        }
    }

    private UserCoupon createUserCoupon(ResultSet rs, Statement st2,
            ResultSet rs2) throws SQLException {
        //Timestamp currentDatetime = DatetimeUtils.currentTimestamp();
        UserCoupon userCoupon = new UserCoupon();

        if (String.valueOf(rs.getInt("id")) != null) {
            userCoupon.setCode(String.valueOf(rs.getInt("id")));
        }

        // 用户code需要查询得出来,根据订单号就可以查询出来该订单对应的用户code
        String orderNo = rs.getString("order_no");
        String userCode = "";

        // 从老的数据库中，根据订单id，获取对应的用户id的属性就行了；这里三种方式
        // 获取userCode（user_id是数据库中的字段）的方式一
        String sql = "select user_id from t_order where order_no = '" + orderNo+"'";
        rs2 = st2.executeQuery(sql);
        if (rs2.next()) {
            userCode = rs2.getString("user_id");
        }

        // 获取userCode（user_id是数据库中的字段）的方式二
        // final List<String> list = new ArrayList<>();
        // this.getSqlSession().select(NS + "findUserIdByOrderNo", orderNo,
        // new ResultHandler() {
        // @Override
        // public void handleResult(ResultContext context) {
        // context.getResultObject();
        // list.add((String) context.getResultObject());
        // }
        // });
        // userCode = list.get(0);

        // 将获取的userCode保存到对象userCoupon中
        if (userCode != null && userCode != "") {
            userCoupon.setUserCode(userCode);
        }else{
            logger.error("UserCouponTransfer.transfer:根据订单号orderNo没有查到对应的userCode");
            throw new RuntimeException( "根据订单号orderNo没有查到对应的userCode" );
        }
        // 所以这里需要导入订单表之后，才能执行；否则需要写mapper文件和对应过的sql执行； 方式三
        // UserOrder userOrder = userOrderDAO.findByCode(orderNo);
        // if (userOrder != null) {
        // userCoupon.setUserCode(userOrder.getUserCode());
        // }

        userCoupon.setOrderCode(orderNo);
        userCoupon.setCouponCode(rs.getString("coupon_id"));
        userCoupon.setVerifyCode(rs.getString("verify_code"));
        userCoupon.setUserMobilePhone(rs.getString("mobile"));
        if(rs.getString("plate_number")==null){
            userCoupon.setUserVehiclePlateNumber(rs.getString("plate_number"));
        }else{
            userCoupon.setUserVehiclePlateNumber("");
        }
        userCoupon.setStatus(rs.getString("coupon_status"));
        userCoupon.setActived(true);
        userCoupon.setDeleted(false);
        // coupon_entity_id 外部接口获取的保养券号码id
        userCoupon.setPromotionCode(rs.getString("coupon_entity_id"));

        // 开始时间就是默认的创建时间和更新时间
        Timestamp startDate = rs.getTimestamp("start_date");
        //Timestamp endDatetime = rs.getTimestamp("end_date");// end_date字段没有用
        
        userCoupon.setCreatedDatetime(startDate);
        userCoupon.setUpdatedDatetime(startDate);
        userCoupon.setDescription("");

        return userCoupon;
    }
    
    @Override
    public void postTransfer(Connection strConn) {
        super.postTransfer(strConn);
    }

}
