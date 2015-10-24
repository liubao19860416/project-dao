package com.saike.grape.service.impl.user;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.entity.user.SysUser;
import com.saike.grape.service.api.user.SysUserService;
import com.saike.grape.service.genertic.ServiceBasicTest;

public class SysUserServiceImplTest extends ServiceBasicTest{
    
    @Autowired
    private SysUserService sysUserService;
    
    
    @Test
    public void testUpdateList() throws Exception {
        List<SysUser> SysUsers = null;
        int actual = sysUserService.updatetList(SysUsers);
        Assert.assertEquals(1, actual);
    }
    

}
