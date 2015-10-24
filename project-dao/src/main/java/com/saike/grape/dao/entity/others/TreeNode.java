package com.saike.grape.dao.entity.others;

import org.apache.ibatis.type.Alias;

import com.saike.grape.dao.entity.basic.BaseEntity;

/**
 * 品牌,城市父类
 */

@Alias( "treeNode" )
public class TreeNode extends BaseEntity {

    private static final long serialVersionUID = -1759740257788182818L;
    
    private String name;            //名称
    private String parentCode;      //父编号
    private int level;              //层级
    private String fullPath;        //全路径
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getParentCode() {
        return parentCode;
    }
    
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public String getFullPath() {
        return fullPath;
    }
    
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
    
}
