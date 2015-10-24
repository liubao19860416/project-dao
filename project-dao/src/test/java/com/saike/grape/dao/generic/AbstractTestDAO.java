package com.saike.grape.dao.generic;

import com.saike.grape.dao.entity.basic.BaseEntity;
import com.saike.grape.dao.generic.GenericDAOBatisImpl;

public abstract class AbstractTestDAO<E extends BaseEntity,T extends AbstractTestDAO<E, T>>
        extends GenericDAOBatisImpl<E, T> {

}
