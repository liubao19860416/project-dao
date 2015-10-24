package com.saike.grape.dao.datas.transfer.others;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.others.CouponDAO;
import com.saike.grape.dao.datas.transfer.abs.AbstractTransfer;
import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.utils.DAOConstants;
import com.saike.grape.dao.utils.DAODatetimeUtils;

/**
 * 保养券Coupon数据导入（对应旧表t_coupon）
 */
@Repository
public class CouponTransfer extends AbstractTransfer {

    private static final Logger logger = LoggerFactory
            .getLogger(CouponTransfer.class);

    @Autowired
    CouponDAO couponDAO;

    // private static final String NS = "datas.transfer.CouponTransferMapper.";

    @Override
    public void transfer(Connection srcConn) {

        // List<Coupon> list = couponDAO.selectAll();

        // Map<String, Object> params = newParamsMap();
        // super.getSqlSession().insert(NS + "insertCoupon", params);

        Statement st = null;
        ResultSet rs = null;
        int i = 0;

        try {
            List<Coupon> couponList = new ArrayList<Coupon>();

            // 从老的数据库表中读出我们需要的数据
            st = srcConn.createStatement();
            String sql = "select * from t_coupon";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                couponList.add(createCoupon(rs));
                i++;
                if (i % 100 == 0) {
                    // 需要插入到新的数据库表中（basic类中已经默认设置了批量插入操作的值为100每次）
                    couponDAO.insert(couponList);
                    Thread.sleep(1);
                    couponList.clear();
                }
            }

            // 将余数不足的记录插入到数据库表中
            if (couponList.size() != 0) {
                // 这里需要插入到新的数据库表中
                couponDAO.insert(couponList);
                Thread.sleep(1);
                couponList.clear();
            }

        } catch (Exception ex) {
            logger.error("CouponTransfer.transfer exception: ", ex);
            throw new RuntimeException( ex );
        } finally {
            closeStatement(st);
            closeResultSet(rs);
        }

    }

    @SuppressWarnings("all")
    private Coupon createCoupon(ResultSet rs) throws SQLException {

        Timestamp currentDatetime = DAODatetimeUtils.currentTimestamp();

        Coupon coupon = new Coupon();

        if (String.valueOf(rs.getInt("id")) != null) {
            coupon.setCode(String.valueOf(rs.getInt("id")));
        }

        coupon.setName(rs.getString("coupon_type"));// 默认值？

        // 需要查询
        coupon.setFitToMinKm(DAOConstants.KM_MIN);
        coupon.setFitToMaxKm(DAOConstants.KM_MAX);
        coupon.setFitToMinDaysUsed(DAOConstants.DAYS_MIN);
        coupon.setFitToMaxDaysUsed(DAOConstants.DAYS_MAX);

        // 暂时使用的默认值
        coupon.setFitToEmissionVolume(DAOConstants.FIT_TO_ALL);
        coupon.setFitToVehicleScope(DAOConstants.FIT_TO_ALL);
        // 当前只是上海，更新code格式
        coupon.setFitToCity(DAOConstants.CODE_SEPARATOR + SH_CITY_CODE
                + DAOConstants.CODE_SEPARATOR);

        if (rs.getTimestamp("expired_date") != null) {
            //根据结束时间，算出开始时间（结束时间不为空的情况）
            Timestamp endDatetime = DAODatetimeUtils.parseTimestamp(DAODatetimeUtils
                    .formatDate(rs.getTimestamp("expired_date")));
            //设置结束时间
            coupon.setEndDatetime(endDatetime);

            // 计算得出开始时间（8540为最大值）
            String validDays = rs.getString("valid_days");
            if (validDays != null && validDays != "") {
                int days = Integer.parseInt(validDays);
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDatetime);
                cal.add(Calendar.DATE, -days);
                //设置开始时间
                coupon.setBeginDatetime(new Timestamp(cal.getTimeInMillis()));
                coupon.setValidInDays(days);
            }
        }else if(rs.getTimestamp("modify_date")!=null){
            //根据开始时间，计算结束时间（修改时间不为空的情况）
            Timestamp beginDatetime = DAODatetimeUtils.parseTimestamp(DAODatetimeUtils
                    .formatDate(rs.getTimestamp("modify_date")));
            //设置开始时间
            coupon.setBeginDatetime(beginDatetime);
            
            // 计算得出结束时间
            String validDays = rs.getString("valid_days");
            if (validDays != null && validDays != "") {
                int days = Integer.parseInt(validDays);
                Calendar cal = Calendar.getInstance();
                cal.setTime(beginDatetime);
                cal.add(Calendar.DATE, days);
            
                //设置结束时间
                coupon.setEndDatetime(new Timestamp(cal.getTimeInMillis()));
                coupon.setValidInDays(days);
            }
         }else{
        	 // 计算得出结束时间(过期时间和修改时间都为空的情况，未设置时间)
             String validDays = rs.getString("valid_days");
             if (validDays != null && validDays != "") {
                 int days = Integer.parseInt(validDays);
             //设置有效期天数
             coupon.setValidInDays(days);
             }
         }

        coupon.setMoneyAmount(rs.getFloat("amount"));
        coupon.setColor1((rs.getString("color_value1")));
        coupon.setColor2((rs.getString("color_value2")));
        coupon.setRuleBrief(rs.getString("summary"));
        coupon.setRuleDetail(rs.getString("detail_desc"));
        coupon.setDescription(rs.getString("memo"));

        // 判断是否激活？1 激活 0 未激活
        if ("1".equals(rs.getString("states"))) {
            coupon.setActived(true);
            coupon.setDeleted(false);
        } else {
            coupon.setActived(false);
            coupon.setDeleted(true);
        }

        // 创建时间肯定和修改时间一致；而且修改时间一定不为空；更新时间就是修改时间
        coupon.setCreatedDatetime(DAODatetimeUtils.parseTimestamp(DAODatetimeUtils
                .formatDate(rs.getTimestamp("modify_date"))));
        coupon.setUpdatedDatetime(DAODatetimeUtils.parseTimestamp(DAODatetimeUtils
                .formatDate(rs.getTimestamp("modify_date"))));

        return coupon;
    }
    
    @Override
    public void postTransfer(Connection strConn) {
        super.postTransfer(strConn);
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        int days = Integer.parseInt("3");
        Calendar cal = Calendar.getInstance();
        // cal.setTime(new Date());
        cal.add(Calendar.DATE, -days);

        // System.out.println(String.valueOf(null));

        System.out.println(new Timestamp(cal.getTimeInMillis())
                .toLocaleString());
        System.out.println(cal.getTime().toLocaleString());
    }

}
