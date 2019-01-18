package com.duo.my.shop.commons.persistance;

import com.duo.my.shop.commons.dto.BaseResult;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    /**
     * 获得所有实体
     * @return
     */
    List<T> selectAll();
    /**
     * 保存实体
     * @return
     */
    BaseResult save(T entity);
}
