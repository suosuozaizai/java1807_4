package com.duo.my.shop.web.admin.web.controller;

import com.duo.my.shop.commons.abstracts.AbstractController;
import com.duo.my.shop.commons.dto.BaseResult;
import com.duo.my.shop.commons.dto.PageInfo;
import com.duo.my.shop.domain.TbContent;
import com.duo.my.shop.domain.TbUser;
import com.duo.my.shop.web.admin.service.TbContentService;
import com.duo.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController extends AbstractController<TbContent,TbContentService> {

    @Override
    @RequestMapping(value="list",method = RequestMethod.GET)
    public String list(){
       /* List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);*/
        return "content_list";
    }


    @ModelAttribute
    public TbContent getTbContent(Long id){

        TbContent tbContent = new TbContent();
        if(id!=null){
            tbContent =  service.getTbContentById(id);
        }

        return tbContent;
    }


    @Override
    @RequestMapping(value="form",method = RequestMethod.GET)
    public String form(){

        return "content_form";
    }


    @Override
    @RequestMapping(value="save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = service.save(tbContent);
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //如果表单验证通过了,把封装了具体状态码和信息的baseResult对象转递到目标页面
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        else{
            //如果表单验证没有通过
            //返回错误信息，并且回到新增页面
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }



    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="deletemulti",method = RequestMethod.POST)
    public BaseResult deletemulti(String ids){
        BaseResult baseResult = service.deleteTbContent(ids);
        return baseResult;
    }
    @ResponseBody
    @RequestMapping(value="page",method=RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null ? 1 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        PageInfo<TbContent> pageInfo =  service.page(draw,start,length,tbContent);
        return pageInfo;
    }

    @RequestMapping(value="detail",method = RequestMethod.GET)
    public String detail(){
        return "content_detail";
    }



}
