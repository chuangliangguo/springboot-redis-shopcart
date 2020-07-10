package com.spacemall.web.controller;

import com.spacemall.core.common.RestfulEntity;
import com.spacemall.core.exception.ApiErrorCode;
import com.spacemall.core.exception.ApiException;
import com.spacemall.core.util.GsonHelper;
import com.spacemall.model.dto.requestDto.ReqShopCartDto;
import com.spacemall.model.entity.ShoppingCartEntity;
import com.spacemall.web.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author gcl
 * @date 2020/7/3 10:40
 * @description
 */
@Api(tags = "用户购物车相关接口", value = "用户购物车相关接口")
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @ApiOperation(value = "添加产品到购物车")
    @RequestMapping(method = RequestMethod.POST)
    public RestfulEntity<String> addProductToShopCart(@Valid @RequestBody ReqShopCartDto reqShopCartDto) {
        log.info("ShoppingCartController addProductToShopCart start: reqShopCartDto:{}", GsonHelper.toGson(reqShopCartDto));
        try {
            String str = shoppingCartService.addProductToShopCart(reqShopCartDto);
            return RestfulEntity.getSuccess(str);
        } catch (ApiException e) {
            log.error("ShoppingCartController addProductToShopCart error: repeat addition products to redis");
            return RestfulEntity.getFailure(ApiErrorCode.REPEAT_ADDITION_PRODUCT_ERROR);
        } catch (Exception e) {
            log.error("ShoppingCartController addProductToShopCart error:", e);
            return RestfulEntity.getFailure(ApiErrorCode.SYSTEM_ERROR);
        }
    }

    @ApiOperation(value = "查询购物车产品")
    @RequestMapping(method = RequestMethod.GET)
    public RestfulEntity<List<ShoppingCartEntity>> getProductFromShopCart(@RequestParam String userId,
                                                                          @RequestParam String userName) {
        log.info("ShoppingCartController getProductFromShopCart start: userId:{},userName:{}", userId, userName);
        try {
            List<ShoppingCartEntity> shoppingCartEntityList = shoppingCartService.getProductFromShopCart(userId, userName);
            return RestfulEntity.getSuccess(shoppingCartEntityList);
        } catch (Exception e) {
            log.error("ShoppingCartController getProductFromShopCart error:", e);
            return RestfulEntity.getFailure(ApiErrorCode.SYSTEM_ERROR);
        }
    }

    @ApiOperation(value = "移除购物车中某个产品")
    @RequestMapping(method = RequestMethod.DELETE)
    public RestfulEntity<List<ShoppingCartEntity>> deleteProductFromShopCart(@Valid @RequestBody ReqShopCartDto reqShopCartDto) {
        log.info("ShoppingCartController deleteProductFromShopCart start: ReqShopCartDto:{}", reqShopCartDto);
        try {
            List<ShoppingCartEntity> shoppingCartEntityList = shoppingCartService.deleteProductFromShopCart(reqShopCartDto);
            return RestfulEntity.getSuccess(shoppingCartEntityList);
        } catch (ApiException e) {
            log.error("ShoppingCartController deleteProductFromShopCart error: the user don't have the product");
            return RestfulEntity.getFailure(ApiErrorCode.REPEAT_ADDITION_PRODUCT_ERROR);
        } catch (Exception e) {
            log.error("ShoppingCartController deleteProductFromShopCart error:", e);
            return RestfulEntity.getFailure(ApiErrorCode.SYSTEM_ERROR);
        }
    }


}
