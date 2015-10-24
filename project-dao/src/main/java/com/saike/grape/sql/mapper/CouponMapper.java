package com.saike.grape.sql.mapper;

import com.saike.grape.sql.po.Coupon;
import com.saike.grape.sql.po.CouponExample;
import com.saike.grape.sql.po.CouponWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponWithBLOBs record);

    int insertSelective(CouponWithBLOBs record);

    List<CouponWithBLOBs> selectByExampleWithBLOBs(CouponExample example);

    List<Coupon> selectByExample(CouponExample example);

    CouponWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponWithBLOBs record, @Param("example") CouponExample example);

    int updateByExampleWithBLOBs(@Param("record") CouponWithBLOBs record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(CouponWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CouponWithBLOBs record);

    int updateByPrimaryKey(Coupon record);
}