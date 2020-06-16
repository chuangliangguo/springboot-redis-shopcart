package com.example.demo.service;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.util.PageUtil;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {

    PageUtil queryEmployee(Integer page, Integer size);

    Integer updateEmployee(EmployeeEntity employeeEntity);

    Integer addEmployee(EmployeeEntity employeeEntity);

    Integer deleteEmployee(Integer id);


}
