package com.saike.grape.dao.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saike.grape.dao.api.user.SysUserDAO;
import com.saike.grape.dao.entity.user.SysUser;
import com.saike.grape.dao.generic.GenericDAOTest;
import com.saike.grape.dao.utils.DAOUtils;

public class SysUserDAOImplTest extends GenericDAOTest {

    @Autowired
    private SysUserDAO sysUserDAO;

    /**
     * 测试基本的增删改查
     */
    @Test
    public void testInsert() throws Exception {
        SysUser entity=new SysUser();
        entity.setCode(DAOUtils.uuid());
        entity.setUserId(0001);
        entity.setAccount("00010001");
        entity.setPassword("123456");
        entity.setUserName("00010001");
        sysUserDAO.insert(entity);
    }
    @Test
    public void testInsertList() throws Exception {
        SysUser entity1=new SysUser();
        entity1.setCode(DAOUtils.uuid());
        entity1.setUserId(0001);
        entity1.setAccount("00010001");
        entity1.setPassword("123456");
        entity1.setUserName("00010001");
        
        SysUser entity2=new SysUser();
        entity2.setId(8l);
        entity2.setCode(DAOUtils.uuid());
        entity2.setUserId(0002);
        entity2.setAccount("00020002");
        entity2.setPassword("123456");
        entity2.setUserName("00020002");
        
        List<SysUser> entitys=new ArrayList<SysUser>();
        entitys.add(entity1);
        entitys.add(entity2);
        
        sysUserDAO.insert(entitys);
    }
    
    @Test
    public void testDelete() throws Exception {
        SysUser entity=new SysUser();
        entity.setId(8l);
        sysUserDAO.delete(entity);
    }
    
    @Test
    public void testDeleteList() throws Exception {
        SysUser entity=new SysUser();
        entity.setId(6l);
        List<SysUser> entitys=new ArrayList<SysUser>();
        entitys.add(entity);
        sysUserDAO.delete(entitys);
    }
    
    @Test
    public void testUpdate() throws Exception {
        SysUser entity=new SysUser();
        entity.setId(8l);
        entity.setAccount("00000000");
        entity.setPassword("123456");
        entity.setUserName("00000000");
        entity.setDescription("描述信息");
        entity.setActived(false);
        entity.setDeleted(true);
        sysUserDAO.update(entity);
    }
    
    @Test
    public void testUpdateList() throws Exception {
        SysUser entity=new SysUser();
        entity.setId(8l);
        entity.setAccount("00000000");
        entity.setPassword("123456");
        entity.setUserName("00000000");
        entity.setDescription("描述信息");
        entity.setActived(false);
        entity.setDeleted(true);
        List<SysUser> entitys=new ArrayList<SysUser>();
        entitys.add(entity);
        sysUserDAO.update(entitys);
    }
}
