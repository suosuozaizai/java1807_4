package com.duo.my.shop.commons.persistance;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询所有用户
     */
    List<T> selectAll();
}
