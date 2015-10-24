package com.saike.grape.service.api.user;

import java.util.List;

import com.saike.grape.dao.entity.user.SysUser;
import com.saike.grape.service.genertic.GenericService;
import com.saike.grape.service.vo.user.SysUserViewObject;

public interface SysUserService extends GenericService<SysUserViewObject, SysUser> {
    
    public int updatetList(List<SysUser> SysUsers);
}
