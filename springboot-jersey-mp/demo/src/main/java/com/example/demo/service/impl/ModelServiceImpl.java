package com.example.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.dao.ModelDao;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.ModelService;
import com.example.demo.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Resource
    ModelDao modelDao;

    @Override
    public PageUtil queryEmployee(Integer page, Integer size){
        PageUtil pageUtil = new PageUtil();
        Page<EmployeeEntity> pageObj = new Page<>(page, size);
        List<EmployeeEntity>  entityList = modelDao.selectPage(pageObj,null);
        pageUtil.setPage(page);
        pageUtil.setSize(size);
        pageUtil.setTotalCount(pageObj.getTotal());
        pageUtil.setTotalPages(pageObj.getPages());
        pageUtil.setList(entityList);
        return pageUtil;
    }

    @Override
    public Integer updateEmployee(EmployeeEntity employeeEntity){
        Integer integer = modelDao.updateById(employeeEntity);
        return integer;
    }

    @Override
    public Integer addEmployee(EmployeeEntity employeeEntity){
        Integer integer = modelDao.insert(employeeEntity);
        return integer;
    }


    @Override
    public Integer deleteEmployee(Integer id){
        Integer integer = modelDao.deleteById(id);
        return integer;
    }
}
