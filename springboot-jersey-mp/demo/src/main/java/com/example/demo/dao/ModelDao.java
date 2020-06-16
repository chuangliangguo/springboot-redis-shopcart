package com.example.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.model.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelDao extends BaseMapper<EmployeeEntity> {

//    ModelEntity queryUser(PageUtil pageObj, null);
//
//    Integer updateUser(@Param("id") Integer id);
//
//    Integer addUser(ModelEntity modelEntity);
//
//    Integer deleteUser(@Param("id") Integer id);

}
