package com.duo.my.shop.commons.abstracts;

import com.duo.my.shop.commons.persistance.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractServiceImpl<T,S extends BaseDao> {
    @Autowired
    protected S dao;

    public abstract List<T> selectAll();

}
