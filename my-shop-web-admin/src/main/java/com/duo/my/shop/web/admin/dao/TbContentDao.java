package com.duo.my.shop.web.admin.dao;

import com.duo.my.shop.commons.persistance.BaseDao;
import com.duo.my.shop.commons.persistance.BaseEntity;
import com.duo.my.shop.domain.TbContent;
import com.duo.my.shop.domain.TbUser;

import java.util.List;
import java.util.Map;

public interface TbContentDao extends BaseDao<TbContent> {



    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    TbContent selectById(Long id);

    /**
     * 把javabean添加到数据库里
     * @param
     */
    void insertTbContent(TbContent tbContent);

    /**
     * 删除的方法
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新
     * @param
     */
    void updateTbContent(TbContent tbContent);



    List<TbContent> selectBySearch(TbContent tbContent);

    void deleteTbContent(String[] ids_arr);

    int count(TbContent tbContent);

    List<TbContent> selectByPage(Map<String,Object> map);


}
