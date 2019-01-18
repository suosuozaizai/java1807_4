package com.duo.my.shop.commons.abstracts;

import com.duo.my.shop.commons.persistance.BaseEntity;
import com.duo.my.shop.commons.persistance.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public abstract class AbstractController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S service;

    public abstract String list();


    public abstract String form();

    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

}
