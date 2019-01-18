package com.duo.my.shop.domain;

import com.duo.my.shop.commons.persistance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TbContentCategory extends BaseEntity {


//    private Long parentId;
    @NotNull(message = "父目录不能为空")
    private TbContentCategory parent;
    @Length(min=1,message = "分类名称不能为空")
    private String name;
    private int status;
    @Length(min=1,message="排序必须介于1-20之间")
    private String sortOrder;
    private Boolean isParent;



}
