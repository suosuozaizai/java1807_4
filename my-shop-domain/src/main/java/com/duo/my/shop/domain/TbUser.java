package com.duo.my.shop.domain;

import com.duo.my.shop.commons.persistance.BaseEntity;
import com.duo.my.shop.commons.util.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class TbUser extends BaseEntity {


    @Length(min=1,message = "用户名不能为空")
    private String username;
    @JsonIgnore
    @Length(min=1,max=8,message = "密码必须是1到8位")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE,message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;



}
