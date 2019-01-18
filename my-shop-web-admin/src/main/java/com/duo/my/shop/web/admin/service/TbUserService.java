package com.duo.my.shop.web.admin.service;

import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.dto.PageInfo;
import com.duo.my.shop.commons.persistance.BaseService;
import com.duo.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {


    /**
     * 登录验证
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);



    /**
     * 根据关键字搜索
     * @param tbUser
     * @return
     */
    List<TbUser> searchTbUsers(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     */
    BaseResult deleteTbUsers(String ids);

    /**
     * 带着搜索条件的分页
     * @param draw
     * @param start
     * @param length
     * @param tbUser
     * @return
     */
    PageInfo<TbUser> page(int draw, int start, int length,TbUser tbUser);

    /**
     * 获得满足条件的的记录总数
     * @param tbUser
     * @return
     */
    int count(TbUser tbUser);

    TbUser getUserById(Long id);
}
