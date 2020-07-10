package com.spacemall.model.dto.requestDto;

import com.spacemall.model.entity.ShoppingCartEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gcl
 * @date 2020/7/3 11:29
 * @description
 */
@Data
public class ReqShopCartDto {

    @NotEmpty(message = "用户id不能为空")
    private String id;
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "购物车不能为空")
    private List<ShoppingCartEntity> shoppingCartEntities;
}
