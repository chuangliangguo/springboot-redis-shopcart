package com.spacemall.web.service;

import com.spacemall.model.dto.requestDto.ReqShopCartDto;
import com.spacemall.model.entity.ShoppingCartEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gcl
 * @date 2020/7/3 11:25
 * @description
 */
@Service
public interface ShoppingCartService {

    String addProductToShopCart (ReqShopCartDto reqShopCatDto)throws Exception;

    List<ShoppingCartEntity> getProductFromShopCart(String userId, String userName);

    List<ShoppingCartEntity> deleteProductFromShopCart(ReqShopCartDto reqShopCartDto)throws Exception;
}
