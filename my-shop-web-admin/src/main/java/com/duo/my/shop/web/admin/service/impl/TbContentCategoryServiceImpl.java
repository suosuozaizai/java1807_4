package com.duo.my.shop.web.admin.service.impl;

import com.duo.my.shop.commons.abstracts.AbstractServiceImpl;
import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.validator.BeanValidator;
import com.duo.my.shop.domain.TbContentCategory;
import com.duo.my.shop.web.admin.dao.TbContentCategoryDao;
import com.duo.my.shop.web.admin.dao.TbContentDao;
import com.duo.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService {

   @Override
   @Transactional(readOnly = true)
    public List<TbContentCategory> selectAll() {
        return dao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TbContentCategory> selectByParentId(Long id) {
        return dao.selectByParentId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TbContentCategory selectById(Long id) {
        return dao.selectById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult delete(String id) {
       if(StringUtils.isNotBlank(id)){
           dao.delete(id);
           return BaseResult.success("删除成功");
       }
        return BaseResult.fail("删除失败");
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory tbContentCategory) {
        //使用表单验证工具验证，获得结果字符串
        String validator = BeanValidator.validator(tbContentCategory);
        BaseResult baseResult = null;
        if(validator==null){
            //验证通过
            //判断是新增还是编辑
            if(tbContentCategory.getId()==null){
                //修改父目录为isParent
                if(tbContentCategory.getParent()!=null&&tbContentCategory.getParent().getId()!=null) {
                    //如果添加的是根级目录（跟myshop同级，那么就没有父节点）
                    Long parent_id = tbContentCategory.getParent().getId();
                    TbContentCategory contentCategoryParent = dao.selectById(parent_id);
                    contentCategoryParent.setIsParent(true);
                    dao.update(contentCategoryParent);
                    //如果不是根目录，该目录不是一个父目录
                    tbContentCategory.setIsParent(false);
                }else{
                    //如果是根级目录的目录，则设置父目录的id和name
                    tbContentCategory.getParent().setId(0L);
                    tbContentCategory.getParent().setName("/");
                    //成为一个父目录
                    tbContentCategory.setIsParent(true);
                }

                //新增
                tbContentCategory.setCreated(new Date());
                tbContentCategory.setUpdated(new Date());

                dao.insert(tbContentCategory);
                baseResult = BaseResult.success("添加分类成功");

            }else{
                //编辑
                tbContentCategory.setUpdated(new Date());
                dao.update(tbContentCategory);
                baseResult = BaseResult.success("编辑分类成功");
            }

        }

        else{
            //验证不通过
            baseResult = BaseResult.fail(validator);
        }

        return baseResult;
    }



}
