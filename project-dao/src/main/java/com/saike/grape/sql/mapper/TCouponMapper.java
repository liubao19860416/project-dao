package com.saike.grape.sql.mapper;

import com.saike.grape.sql.po.TCoupon;
import com.saike.grape.sql.po.TCouponWithBLOBs;

public interface TCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TCouponWithBLOBs record);

    int insertSelective(TCouponWithBLOBs record);

    TCouponWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TCouponWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCouponWithBLOBs record);

    int updateByPrimaryKey(TCoupon record);
}