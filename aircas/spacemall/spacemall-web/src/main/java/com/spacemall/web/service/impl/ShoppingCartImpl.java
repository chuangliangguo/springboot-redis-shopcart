package com.spacemall.web.service.impl;

import com.spacemall.core.exception.ApiErrorCode;
import com.spacemall.core.exception.ApiException;
import com.spacemall.core.util.GsonHelper;
import com.spacemall.model.dto.requestDto.ReqShopCartDto;
import com.spacemall.model.entity.ShoppingCartEntity;
import com.spacemall.web.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gcl
 * @date 2020/7/3 11:26
 * @description
 */
@Service
@Slf4j
public class ShoppingCartImpl implements ShoppingCartService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public String addProductToShopCart(ReqShopCartDto reqShopCatDto) throws Exception {
        log.info("ShoppingCartImpl addProductToShopCart start: reqShopCatDto:{}", reqShopCatDto);
        String userId = reqShopCatDto.getId();
        String userName = reqShopCatDto.getUserName();
        List<ShoppingCartEntity> shoppingCatEntities = reqShopCatDto.getShoppingCartEntities();
        String keyUser = userId + "_" + userName;
        Boolean key = redisTemplate.hasKey(keyUser);
        if (!key) {
            String str = addProductsToRedis(keyUser, shoppingCatEntities);
            return str;
        } else {
            List<Object> objectList = redisTemplate.opsForList().range(keyUser, 0, -1);
            List<ShoppingCartEntity> shoppingCatEntityList = convertObjectToShopCartEntity(objectList);
            for (ShoppingCartEntity shopCat : shoppingCatEntityList) {
                for (ShoppingCartEntity sCat : shoppingCatEntities) {
                    if (shopCat.getProductId().equals(sCat.getProductId())) {
                        throw new ApiException(ApiErrorCode.REPEAT_ADDITION_PRODUCT_ERROR);
                    }
                }
            }
            String str = addProductsToRedis(keyUser, shoppingCatEntities);
            return str;
        }
    }

    @Override
    public List<ShoppingCartEntity> getProductFromShopCart(String userId, String userName) {
        log.info("ShoppingCatImpl getProductFromShopCat start: userId:{},userName:{}", userId, userName);
        String keyUser = userId + "_" + userName;
        if (!redisTemplate.hasKey(keyUser)) {
            return null;
        } else {
            List<Object> objectList = redisTemplate.opsForList().range(keyUser, 0, -1);
            List<ShoppingCartEntity> shoppingCatEntityList = convertObjectToShopCartEntity(objectList);
            return shoppingCatEntityList;
        }
    }

    @Override
    public List<ShoppingCartEntity> deleteProductFromShopCart(ReqShopCartDto reqShopCartDto) throws Exception {
        log.info("ShoppingCartImpl deleteProductFromShopCart start: reqShopCartDto:{}", GsonHelper.toGson(reqShopCartDto));
        String keyUser = reqShopCartDto.getId() + "_" + reqShopCartDto.getUserName();
        List<ShoppingCartEntity> shoppingCartEntities = reqShopCartDto.getShoppingCartEntities();
        for (ShoppingCartEntity shopCartEntity : shoppingCartEntities) {
            Long remove = redisTemplate.opsForList().remove(keyUser, 0, shopCartEntity);
            if (remove <= 0) {
                log.error("ShoppingCartImpl deleteProductFromShopCart: redis remove product fail,userId:{},shopCartEntity:{}", reqShopCartDto.getId(), GsonHelper.toGson(shopCartEntity));
                throw new ApiException(ApiErrorCode.NO_PRODUCT_ERROR);
            }
        }
        List<Object> objectList = redisTemplate.opsForList().range(keyUser, 0, -1);
        List<ShoppingCartEntity> shoppingCatEntityList = convertObjectToShopCartEntity(objectList);
        return shoppingCatEntityList;
    }

    private String addProductsToRedis(String key, List<ShoppingCartEntity> shoppingCatEntities) throws Exception {
        long num = redisTemplate.opsForList().rightPushAll(key, shoppingCatEntities.toArray());
        if (num <= 0) {
            log.error("ShoppingCartImpl addProductToShopCart: redis error, add products to redis fail");
            throw new Exception();
        }
        return "产品已成功加入购物车";
    }

    private List<ShoppingCartEntity> convertObjectToShopCartEntity(List<Object> objectList) {
        List<ShoppingCartEntity> shoppingCatEntityList = objectList.stream().map(item -> {
            ShoppingCartEntity shoppingCatEntity = (ShoppingCartEntity) item;
            return shoppingCatEntity;
        }).collect(Collectors.toList());
        return shoppingCatEntityList;
    }

}
