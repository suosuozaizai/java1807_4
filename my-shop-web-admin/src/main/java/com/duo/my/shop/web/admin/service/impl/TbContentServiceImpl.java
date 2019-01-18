package com.duo.my.shop.web.admin.service.impl;

import com.duo.my.shop.commons.abstracts.AbstractServiceImpl;
import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.dto.PageInfo;
import com.duo.my.shop.commons.validator.BeanValidator;
import com.duo.my.shop.domain.TbContent;
import com.duo.my.shop.domain.TbUser;
import com.duo.my.shop.web.admin.dao.TbContentDao;
import com.duo.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl extends AbstractServiceImpl<TbContent,TbContentDao> implements TbContentService {



    @Override
    public List<TbContent> selectAll() {
        return dao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        //BaseResult baseResult = checkTbContent(tbContent);
        String validator = BeanValidator.validator(tbContent);
        BaseResult baseResult = null;
        if(validator==null){
            //验证通过
            //新增内容
            if (tbContent.getId() == null) {
                tbContent.setCreated(new Date());
                tbContent.setUpdated(new Date());
                dao.insertTbContent(tbContent);

            } else {
                //编辑内容
                dao.updateTbContent(tbContent);

            }
            baseResult = BaseResult.success("保存内容成功");
        }
        else{
            //验证不通过
            baseResult = BaseResult.fail(validator);
        }



        return baseResult;
    }



    @Override
    public BaseResult deleteTbContent(String ids) {
        BaseResult baseResult = null;
        //验证ids不为空
        if(StringUtils.isNotBlank(ids)){
            String[] ids_arr = ids.split(",");
            dao.deleteTbContent(ids_arr);
            baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @Override
    public PageInfo<TbContent> page(int draw, int start, int length, TbContent tbContent) {
        PageInfo<TbContent> pageInfo = new PageInfo<>();
        int count = dao.count(tbContent);
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        //搜索条件是在tbUser内，把tbUser封装在map中
        map.put("tbContent",tbContent);
        pageInfo.setData(dao.selectByPage(map));
        return pageInfo;
    }

    @Override
    public int count(TbContent tbContent) {
        return dao.count(tbContent);
    }

    @Override
    public TbContent getTbContentById(Long id) {

            return dao.selectById(id);
    }


    /**
     * 验证表单数据
     *
     * @return BaseResult对象
     */
    public BaseResult checkTbContent(TbContent tbContent) {
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbContent.getTitle())) {
            baseResult = BaseResult.fail("标题不能为空，请重写填写");
        }


        return baseResult;
    }
}
