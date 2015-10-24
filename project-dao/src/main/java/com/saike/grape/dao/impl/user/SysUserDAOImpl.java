package com.saike.grape.dao.impl.user;

import org.springframework.stereotype.Repository;

import com.saike.grape.dao.api.user.SysUserDAO;
import com.saike.grape.dao.entity.user.SysUser;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

/**
 * 测试（用户信息表相关）的DAO接口
 */
@Repository
public class SysUserDAOImpl extends
        GenericDAOBatisImpl<SysUser, SysUserDAOImpl> implements SysUserDAO {

}