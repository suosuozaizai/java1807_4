package com.duo.my.shop.commons.persistance;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;
}
