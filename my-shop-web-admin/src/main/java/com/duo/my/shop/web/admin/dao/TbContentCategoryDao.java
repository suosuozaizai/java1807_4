package com.duo.my.shop.web.admin.dao;

import com.duo.my.shop.commons.persistance.BaseDao;
import com.duo.my.shop.commons.persistance.BaseEntity;
import com.duo.my.shop.domain.TbContentCategory;
import junit.framework.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao extends BaseDao<TbContentCategory> {




    List<TbContentCategory> selectByParentId(Long id);

    TbContentCategory selectById(Long id);

    void insert(TbContentCategory tbContentCategory);

    void update(TbContentCategory tbContentCategory);

    void delete(String id);
}
