package com.example.demo.controller;

import com.example.demo.common.RestfulEntity;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.ModelService;
import com.example.demo.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Api(tags = "jersey模板controller")
@Path("/test")
@Slf4j
@Component
public class JerseyModelController {

    @Autowired
    ModelService modelService;

    @ApiOperation("获取用户信息")
    @Path("/employee")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestfulEntity<PageUtil> queryEmployee(@QueryParam(value = "page") Integer page,
                                                 @QueryParam(value = "size") Integer size) {
        log.info("JerseyModelController queryEmployee start: page:{},size:{}", page, size);
        PageUtil pageUtil = modelService.queryEmployee(page, size);
        return RestfulEntity.getSuccess(pageUtil);
    }

    @ApiOperation("新增用户信息")
    @Path("/employee")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addEmployee(EmployeeEntity employeeEntity) {
        Integer integer = modelService.addEmployee(employeeEntity);
        return integer;
    }

    @ApiOperation("更新用户信息")
    @Path("/employee")
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer updateEmployee(EmployeeEntity employeeEntity) {
        Integer integer = modelService.updateEmployee(employeeEntity);
        return integer;
    }

    @ApiOperation("删除用户信息")
    @Path("/employee")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer deleteEmployee(@RequestParam("id") Integer id) {
        Integer integer = modelService.deleteEmployee(id);
        return integer;
    }
}
