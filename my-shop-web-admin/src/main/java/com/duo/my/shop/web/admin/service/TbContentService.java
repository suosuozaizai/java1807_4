package com.duo.my.shop.web.admin.service;

import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.dto.PageInfo;
import com.duo.my.shop.commons.persistance.BaseService;
import com.duo.my.shop.domain.TbContent;
import com.duo.my.shop.domain.TbUser;

import java.util.List;

public interface TbContentService extends BaseService<TbContent> {







    /**
     * 批量删除
     * @param ids
     */
    BaseResult deleteTbContent(String ids);

    /**
     * 带着搜索条件的分页
     * @param draw
     * @param start
     * @param length
     * @param      * @return
     */
    PageInfo<TbContent> page(int draw, int start, int length, TbContent tbContent);

    /**
     * 获得满足条件的的记录总数
     * @param
     * @return
     */
    int count(TbContent tbContent);

    TbContent getTbContentById(Long id);



}
