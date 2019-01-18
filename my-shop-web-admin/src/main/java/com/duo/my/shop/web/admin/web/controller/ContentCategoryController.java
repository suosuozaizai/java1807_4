package com.duo.my.shop.web.admin.web.controller;

import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.domain.TbContentCategory;
import com.duo.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory;
        if(id==null){
            tbContentCategory = new TbContentCategory();
        }
        else {
            tbContentCategory = tbContentCategoryService.selectById(id);
        }
        return tbContentCategory;
    }


    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //通过后端的表单验证
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";

        }else{
            //没有通过
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }

    @RequestMapping(value="form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){

        return "content_category_form";
    }


    @ResponseBody
    @RequestMapping(value="detail",method = RequestMethod.POST)
    public List<TbContentCategory> detail(Long id){
        if(id==null){
            id=0L;
        }
        List<TbContentCategory> tbContentCategories   =  tbContentCategoryService.selectByParentId(id);
        return tbContentCategories;
    }

    /**
     * 递归
     * @param sourceList 源集合
     * @param targetList 经过排序后的集合
     * @param parentId  父分类的id
     * @return
     */
    public void sortList(List<TbContentCategory> sourceList,List<TbContentCategory> targetList,Long parentId){
        for (TbContentCategory tbContentCategory : sourceList) {
            if(tbContentCategory.getParent().getId().equals(parentId)){
                targetList.add(tbContentCategory);
                if(tbContentCategory.getIsParent()){
                    for (TbContentCategory contentCategory : sourceList) {
                        if(contentCategory.getParent().getId().equals(tbContentCategory.getId())){
                            sortList(sourceList,targetList,tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public BaseResult delete(String id){
        BaseResult baseResult = tbContentCategoryService.delete(id);
        return baseResult;
    }






}
