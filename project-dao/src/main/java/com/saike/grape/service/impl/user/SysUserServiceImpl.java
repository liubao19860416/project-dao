package com.saike.grape.service.impl.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saike.grape.dao.api.user.SysUserDAO;
import com.saike.grape.dao.entity.user.SysUser;
import com.saike.grape.service.api.user.SysUserService;
import com.saike.grape.service.genertic.GenericServiceImpl;
import com.saike.grape.service.vo.user.SysUserViewObject;

/*
 * 测试事务类
 */
@Service
public class SysUserServiceImpl extends
        GenericServiceImpl<SysUserViewObject, SysUser, SysUserServiceImpl>
        implements SysUserService {

    private Log logger = LogFactory.getLog(SysUserServiceImpl.class);

    @Autowired
    private SysUserDAO sysUserDAO;

    // 对应的DAO使用构造方法的方式自动注入进来
    @Autowired
    public SysUserServiceImpl(SysUserDAO sysUserDAO) {
        super(sysUserDAO);
        this.sysUserDAO = sysUserDAO;
    }

    @SuppressWarnings("unused")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updatetList(List<SysUser> SysUsers) {
        int result = -1;
        if (SysUsers != null && SysUsers.size() > 0) {
            for (SysUser sysUser : SysUsers) {
                if (result == -1) {
                    result = 0;
                }
                result += sysUserDAO.update(sysUser);
            }
        }
        
        
        SysUser entity = new SysUser();
        entity.setId(15l);
        result += sysUserDAO.update(entity);
        // 异常信息
        try {
            int i = 10 / 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("抛出异常信息！！！！！！", e);
            throw new RuntimeException("被0除了！！！");
        }
        entity.setId(16l);
        result += sysUserDAO.update(entity);
        return result;
    }

}
