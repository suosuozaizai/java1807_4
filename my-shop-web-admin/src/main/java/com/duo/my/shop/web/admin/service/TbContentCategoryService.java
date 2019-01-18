package com.duo.my.shop.web.admin.service;

import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.persistance.BaseService;
import com.duo.my.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService extends BaseService<TbContentCategory> {


    List<TbContentCategory> selectByParentId(Long id);

    TbContentCategory selectById(Long id);


    BaseResult delete(String id);
}
