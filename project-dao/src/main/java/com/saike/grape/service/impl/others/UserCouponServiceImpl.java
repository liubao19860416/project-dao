package com.saike.grape.service.impl.others;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saike.grape.dao.api.others.UserCouponDAO;
import com.saike.grape.dao.entity.others.Coupon;
import com.saike.grape.dao.entity.others.UserCoupon;
import com.saike.grape.dao.utils.DAODatetimeUtils;
import com.saike.grape.dao.utils.DAOUtils;
import com.saike.grape.service.api.others.UserCouponService;
import com.saike.grape.service.genertic.GenericServiceImpl;
import com.saike.grape.service.vo.others.UserCouponViewObject;
/**
 * 保养券服务层接口实现类
 */

@Service
public class UserCouponServiceImpl extends
        GenericServiceImpl<UserCouponViewObject, UserCoupon, UserCouponServiceImpl>
        implements UserCouponService {
    
    private Log logger = LogFactory.getLog(UserCouponServiceImpl.class);
    
    @Autowired
    private UserCouponDAO userCouponDAO;
    
    // 对应的DAO使用构造方法的方式自动注入进来
    @Autowired
    public UserCouponServiceImpl(UserCouponDAO userCouponDAO) {
        super(userCouponDAO);
        this.userCouponDAO=userCouponDAO;
    }

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserCouponViewObject> getUserCouponListByOrderCodeList(
            List<String> orderCodes, Integer pageIndex, Integer pageSize) {
        List<UserCoupon> list = userCouponDAO.selectUserCouponsByOrderCodeList(
                orderCodes, pageIndex, pageSize);
        //最终返回结果
        List<UserCouponViewObject> userCouponViewObjects = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (UserCoupon userCoupon : list) {
                UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
                Coupon coupon = userCoupon.getCoupon();
                //userCouponViewObject = this.transToViewObject(userCoupon);
                userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
                userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
                userCouponViewObject.setCouponType(coupon.getName());
                userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
                userCouponViewObject.setColorValue1(coupon.getColor1());
                userCouponViewObject.setColorValue2(coupon.getColor2());
                userCouponViewObject.setSummary(coupon.getRuleBrief());
                userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
                //userCouponViewObject.setOrderCode(orderCode);
                userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
                userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
                userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
                userCouponViewObject.setDescription(coupon.getDescription());
                // userCouponViewObject.setStartDate(DatetimeUtils
                // .formatDate(coupon.getBeginDatetime()));
                // userCouponViewObject.setEndDate(DatetimeUtils
                // .formatDate(coupon.getEndDatetime()));
                //开始和结束时间
                userCouponViewObject.setStartDate(DAODatetimeUtils
                        .formatDate(userCoupon.getCreatedDatetime()));
                Integer validInDays = coupon.getValidInDays();
                Timestamp experiDate = null;
                if (validInDays!=null&&validInDays!=0) {
                    // 计算结束时间
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(userCoupon.getCreatedDatetime());
                    cl.add(Calendar.DATE,validInDays-1);
                    cl.set(Calendar.HOUR_OF_DAY, 23);
                    cl.set(Calendar.MINUTE, 59);
                    cl.set(Calendar.SECOND, 59);
                    experiDate = new Timestamp(cl.getTimeInMillis());
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(experiDate));
                }else{
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(coupon.getEndDatetime()));
                }
                
                userCouponViewObjects.add(userCouponViewObject);
            }
        }
        return userCouponViewObjects;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserCouponViewObject getUserCouponByOrderCode(String orderCode){
        UserCoupon userCoupon = userCouponDAO.selectUserCouponsByOrderCode(orderCode);
        // List<String> orderCodes=new ArrayList<String>();
        // orderCodes.add(orderCode);
        // List<UserCoupon> list =
        // userCouponDAO.selectUserCouponsByOrderCodeList(orderCodes, 1, 1);
        //UserCoupon userCoupon=(list==null||list.size()==0)?new UserCoupon():list.get(0);
        
        UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
        Coupon coupon = userCoupon.getCoupon();
        //userCouponViewObject = this.transToViewObject(userCoupon);
        userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
        userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
        userCouponViewObject.setCouponType(coupon.getName());
        userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
        userCouponViewObject.setColorValue1(coupon.getColor1());
        userCouponViewObject.setColorValue2(coupon.getColor2());
        userCouponViewObject.setSummary(coupon.getRuleBrief());
        userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
        //userCouponViewObject.setOrderCode(orderCode);
        userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
        userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
        userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
        userCouponViewObject.setDescription(coupon.getDescription());
        //开始和结束时间
        userCouponViewObject.setStartDate(DAODatetimeUtils
                .formatDate(userCoupon.getCreatedDatetime()));
        Integer validInDays = coupon.getValidInDays();
        Timestamp experiDate = null;
        if (validInDays!=null&&validInDays!=0) {
            // 计算结束时间
            Calendar cl = Calendar.getInstance();
            cl.setTime(userCoupon.getCreatedDatetime());
            cl.add(Calendar.DATE,validInDays-1);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.SECOND, 59);
            experiDate = new Timestamp(cl.getTimeInMillis());
            userCouponViewObject.setEndDate(DAODatetimeUtils
                    .formatDate(experiDate));
        }else{
            userCouponViewObject.setEndDate(DAODatetimeUtils
                    .formatDate(coupon.getEndDatetime()));
        }
        
        return userCouponViewObject;
        
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserCouponViewObject> getAllUserCoupons() {
        List<UserCoupon> list = userCouponDAO.selectAll();
        //最终返回结果
        List<UserCouponViewObject> userCouponViewObjects = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (UserCoupon userCoupon : list) {
                UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
                Coupon coupon = userCoupon.getCoupon();
                //userCouponViewObject = this.transToViewObject(userCoupon);
                userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
                userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
                userCouponViewObject.setCouponType(coupon.getName());
                userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
                userCouponViewObject.setColorValue1(coupon.getColor1());
                userCouponViewObject.setColorValue2(coupon.getColor2());
                userCouponViewObject.setSummary(coupon.getRuleBrief());
                userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
                //userCouponViewObject.setOrderCode(orderCode);
                userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
                userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
                userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
                userCouponViewObject.setDescription(coupon.getDescription());
                //开始和结束时间
                userCouponViewObject.setStartDate(DAODatetimeUtils
                        .formatDate(userCoupon.getCreatedDatetime()));
                Integer validInDays = coupon.getValidInDays();
                Timestamp experiDate = null;
                if (validInDays!=null&&validInDays!=0) {
                    // 计算结束时间
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(userCoupon.getCreatedDatetime());
                    cl.add(Calendar.DATE,validInDays-1);
                    cl.set(Calendar.HOUR_OF_DAY, 23);
                    cl.set(Calendar.MINUTE, 59);
                    cl.set(Calendar.SECOND, 59);
                    experiDate = new Timestamp(cl.getTimeInMillis());
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(experiDate));
                }else{
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(coupon.getEndDatetime()));
                }
                
                userCouponViewObjects.add(userCouponViewObject);
            }
        }
        
        return userCouponViewObjects;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserCouponViewObject> getUserCouponsByUserCode(
            String userCode, Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        UserCoupon userCouponParam = new UserCoupon();
        userCouponParam.setUserCode(userCode);
        map.put("userCoupon", userCouponParam);
        List<UserCoupon> list = userCouponDAO.selectUserCouponsByConditions(
                map, pageIndex, pageSize);
        //最终返回结果
        List<UserCouponViewObject> userCouponViewObjects = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (UserCoupon userCoupon : list) {
                UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
                Coupon coupon = userCoupon.getCoupon();
                //userCouponViewObject = this.transToViewObject(userCoupon);
                userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
                userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
                userCouponViewObject.setCouponType(coupon.getName());
                userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
                userCouponViewObject.setColorValue1(coupon.getColor1());
                userCouponViewObject.setColorValue2(coupon.getColor2());
                userCouponViewObject.setSummary(coupon.getRuleBrief());
                userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
                //userCouponViewObject.setOrderCode(orderCode);
                userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
                userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
                userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
                userCouponViewObject.setDescription(coupon.getDescription());
                //开始和结束时间
                userCouponViewObject.setStartDate(DAODatetimeUtils
                        .formatDate(userCoupon.getCreatedDatetime()));
                Integer validInDays = coupon.getValidInDays();
                Timestamp experiDate = null;
                if (validInDays!=null&&validInDays!=0) {
                    // 计算结束时间
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(userCoupon.getCreatedDatetime());
                    cl.add(Calendar.DATE,validInDays-1);
                    cl.set(Calendar.HOUR_OF_DAY, 23);
                    cl.set(Calendar.MINUTE, 59);
                    cl.set(Calendar.SECOND, 59);
                    experiDate = new Timestamp(cl.getTimeInMillis());
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(experiDate));
                }else{
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(coupon.getEndDatetime()));
                }
                
                userCouponViewObjects.add(userCouponViewObject);
            }
        }
        return userCouponViewObjects;
    }
    
    /**
     * 生成用户保养券号信息的方法，替代直接的insert方法
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int generateUserCouponForGrape(UserCouponViewObject viewObject) {
        //转换参数类型
        UserCoupon userCoupon=this.transToEntity(viewObject);
        
        //设置其他的默认的属性信息
        userCoupon.setCode(DAOUtils.uuid());//自身code使用uuid生成
        userCoupon.setCreatedDatetime(DAODatetimeUtils.currentTimestamp());
        userCoupon.setUpdatedDatetime(DAODatetimeUtils.currentTimestamp());
        userCoupon.setActived(true);
        userCoupon.setDeleted(false);
        
        //将获取到的保养劵相关信息插入 本地库，返回id
        //userCouponDAO.insert(userCoupon);
        userCouponDAO.insertReturnId(userCoupon);
        return Integer.parseInt(userCoupon.getId()+"");
    }


    @Override
    public List<UserCouponViewObject> getUserCouponsByConditions(
            Map<String, Object> params, Integer pageIndex, Integer pageSize) {
        List<UserCouponViewObject> userCouponViewObjects = new ArrayList<>();
        UserCoupon userCouponParam = new UserCoupon();
        //拷贝参数到对象
        transMapToUserCoupon(userCouponParam,params);
        if(params!=null){
            params.clear();
            params.put("userCoupon", userCouponParam);
        }
        List<UserCoupon> list = userCouponDAO.selectUserCouponsByConditions(
                params, pageIndex, pageSize);
        if(list!=null&&list.size()>0){
            for (UserCoupon userCoupon : list) {
                UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
                Coupon coupon = userCoupon.getCoupon();
                //userCouponViewObject = this.transToViewObject(userCoupon);
                userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
                userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
                userCouponViewObject.setCouponType(coupon.getName());
                userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
                userCouponViewObject.setColorValue1(coupon.getColor1());
                userCouponViewObject.setColorValue2(coupon.getColor2());
                userCouponViewObject.setSummary(coupon.getRuleBrief());
                userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
                //userCouponViewObject.setOrderCode(orderCode);
                userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
                userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
                userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
                userCouponViewObject.setDescription(coupon.getDescription());
                //开始和结束时间
                userCouponViewObject.setStartDate(DAODatetimeUtils
                        .formatDate(userCoupon.getCreatedDatetime()));
                Integer validInDays = coupon.getValidInDays();
                Timestamp experiDate = null;
                if (validInDays!=null&&validInDays!=0) {
                    // 计算结束时间
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(userCoupon.getCreatedDatetime());
                    cl.add(Calendar.DATE,validInDays-1);
                    cl.set(Calendar.HOUR_OF_DAY, 23);
                    cl.set(Calendar.MINUTE, 59);
                    cl.set(Calendar.SECOND, 59);
                    experiDate = new Timestamp(cl.getTimeInMillis());
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(experiDate));
                }else{
                    userCouponViewObject.setEndDate(DAODatetimeUtils
                            .formatDate(coupon.getEndDatetime()));
                }
                
                userCouponViewObjects.add(userCouponViewObject);
            }
        }
        return userCouponViewObjects;
    }
   
    
    @Override
    public Long getUserCouponListCountByOrderCodeList(List<String> orderCodes) {
        return null;
    }


    private void transMapToUserCoupon(UserCoupon userCouponParam,
            Map<String, Object> params) {
        if (params != null) {
            if (!StringUtils.isEmpty((String) params.get("code"))) {
                userCouponParam.setCode((String) params.get("code"));
            }
            if (!StringUtils.isEmpty((String) params.get("couponCode"))) {
                userCouponParam
                        .setCouponCode((String) params.get("couponCode"));
            }
            if (!StringUtils.isEmpty((String) params.get("userCode"))) {
                userCouponParam.setUserCode((String) params.get("userCode"));
            }
            if (!StringUtils.isEmpty((String) params.get("orderCode"))) {
                userCouponParam.setOrderCode((String) params.get("orderCode"));
            }
            if (!StringUtils.isEmpty((String) params.get("status"))) {
                userCouponParam.setStatus((String) params.get("status"));
            }
            if (!StringUtils.isEmpty((String) params.get("userMobilePhone"))) {
                userCouponParam.setUserMobilePhone((String) params
                        .get("userMobilePhone"));
            }
            if (!StringUtils.isEmpty((String) params.get("actived"))) {
                if ("1".equals((String) params.get("actived"))) {
                    userCouponParam.setActived(true);
                    userCouponParam.setDeleted(false);
                }
            }
        }

    }


    @Override
    public UserCouponViewObject getUserCouponById(long id) {
        UserCoupon userCoupon = userCouponDAO.selectUserCouponById(id);
        UserCouponViewObject userCouponViewObject=new UserCouponViewObject();
        Coupon coupon = userCoupon.getCoupon();
        //userCouponViewObject = this.transToViewObject(userCoupon);
        userCouponViewObject.setVerifyCode(userCoupon.getVerifyCode());
        userCouponViewObject.setCouponCode(userCoupon.getCouponCode());
        userCouponViewObject.setCouponType(coupon.getName());
        userCouponViewObject.setAmount(coupon.getMoneyAmount()+"");
        userCouponViewObject.setColorValue1(coupon.getColor1());
        userCouponViewObject.setColorValue2(coupon.getColor2());
        userCouponViewObject.setSummary(coupon.getRuleBrief());
        userCouponViewObject.setDetailDesc(coupon.getRuleDetail());
        //userCouponViewObject.setOrderCode(orderCode);
        userCouponViewObject.setOrderCode(userCoupon.getOrderCode());
        userCouponViewObject.setUserVehiclePlateNumber(userCoupon.getUserVehiclePlateNumber());
        userCouponViewObject.setUserMobilePhone(userCoupon.getUserMobilePhone());
        userCouponViewObject.setDescription(coupon.getDescription());
        //开始和结束时间
        userCouponViewObject.setStartDate(DAODatetimeUtils
                .formatDate(userCoupon.getCreatedDatetime()));
        Integer validInDays = coupon.getValidInDays();
        Timestamp experiDate = null;
        if (validInDays!=null&&validInDays!=0) {
            // 计算结束时间
            Calendar cl = Calendar.getInstance();
            cl.setTime(userCoupon.getCreatedDatetime());
            cl.add(Calendar.DATE,validInDays-1);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.SECOND, 59);
            experiDate = new Timestamp(cl.getTimeInMillis());
            userCouponViewObject.setEndDate(DAODatetimeUtils
                    .formatDate(experiDate));
        }else{
            userCouponViewObject.setEndDate(DAODatetimeUtils
                    .formatDate(coupon.getEndDatetime()));
        }
        return userCouponViewObject;
    }
  
    
}
