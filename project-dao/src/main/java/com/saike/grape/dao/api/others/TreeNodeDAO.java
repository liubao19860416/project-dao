package com.saike.grape.dao.api.others;

import java.util.List;

import com.saike.grape.dao.entity.others.TreeNode;
import com.saike.grape.dao.generic.GenericDAO;

/**
 * 为城市DAO接口服务
 */

public interface TreeNodeDAO<E extends TreeNode> extends GenericDAO<E> {

    public List<TreeNode> getTreeNodeList();
    
    public List<TreeNode> getTreeBranchList( String treePath );
    
    public List<TreeNode> getTreeLeafList( String branchPath );
    
    public List<TreeNode> getTreeNodeByCodeList( List<String> code );
    
}
