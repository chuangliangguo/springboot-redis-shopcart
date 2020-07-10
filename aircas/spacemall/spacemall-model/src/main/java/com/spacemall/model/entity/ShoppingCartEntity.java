package com.spacemall.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gcl
 * @date 2020/7/3 11:20
 * @description
 */
@Data
public class ShoppingCartEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private String prodResolution;
    private String prodLevel;
    private Integer prodScore;
    private Date prodCreateTime;
    private String description;
}
