package com.saike.grape.dao.impl.others;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saike.grape.dao.api.others.TreeNodeDAO;
import com.saike.grape.dao.entity.others.TreeNode;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

public class TreeNodeDAOImpl<E extends TreeNode, T extends TreeNodeDAOImpl<E, T>>
        extends GenericDAOBatisImpl<E, T> implements TreeNodeDAO<E> {

    @SuppressWarnings("unchecked")
    @Override
    public List<TreeNode> getTreeNodeList() {
        Map<String, Object> params = new HashMap<>();

        params.put(VAR_TABLE_NAME, getTableName());
        return (List<TreeNode>) selectList(TreeNodeDAOImpl.class,
                "getTreeNodeList", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TreeNode> getTreeBranchList(String treePath) {
        Map<String, Object> params = new HashMap<>();

        params.put(VAR_TABLE_NAME, getTableName());
        params.put("treePath", treePath);

        return (List<TreeNode>) selectList(TreeNodeDAOImpl.class,
                "getTreeBranchList", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TreeNode> getTreeLeafList(String branchPath) {
        Map<String, Object> params = new HashMap<>();

        params.put(VAR_TABLE_NAME, getTableName());
        params.put("branchPath", branchPath);

        return (List<TreeNode>) selectList(TreeNodeDAOImpl.class,
                "getTreeLeafList", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TreeNode> getTreeNodeByCodeList(List<String> code) {
        Map<String, Object> params = new HashMap<>();

        params.put(VAR_TABLE_NAME, getTableName());
        params.put("code", code);

        return (List<TreeNode>) selectList(TreeNodeDAOImpl.class,
                "getTreeNodeByCodeList", params);
    }

}
