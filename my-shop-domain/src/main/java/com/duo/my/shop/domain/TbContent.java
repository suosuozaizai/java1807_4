package com.duo.my.shop.domain;

import com.duo.my.shop.commons.persistance.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TbContent extends BaseEntity {



//   @NotNull(message = "父级目录不能为空")
//   private Long categoryId;
   @Length(min = 1,max = 10,message = "标题的长度必须介于1个和10个之间")
   private String title;
   @Length(min = 1,max = 10,message = "子标题的长度必须介于1个和10个之间")
   private String subTitle;
   @Length(min = 1,max = 10,message = "标题描述的长度必须介于1个和10个之间")
   private String titleDesc;
   private String url;
   private String pic;
   private String pic2;
   private String content;

   private TbContentCategory parent;



}
