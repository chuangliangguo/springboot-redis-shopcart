package com.spacemall.web.generation;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gcl
 * @date 2020/6/22 19:16
 * @description
 */
@Slf4j
public class CodeGeneration {


    public static void main(String[] args) {

//        String[] models = {"spacemall-model", "spacemall-web"};
//        for (String model : models) {
//            shell(model);
//        }
    }

    private static void shell(String model) {
        File file = new File(model);
        String path = file.getAbsolutePath();
        log.info("path:" + path);
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path + "/src/main/model");//输出文件路径
//        gc.setIdType(IdType.AUTO);//主键策略
        gc.setFileOverride(true);  //是否文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false（是否支持AR模式）
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("gcl");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sDao");
//        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test_db");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"table_company", "table_department"}); // 需要生成的表
        //strategy.setDbColumnUnderline(true);   //指定表名字段是否使用下划线
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);    //开启lombook支持

        strategy.setSuperServiceClass((String) null);
        strategy.setSuperServiceImplClass((String) null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        if ("spacemall-model".equals(model)) {
            pc.setParent("com.spacemall.model");
        }
        if ("spacemall-web".equals(model)) {
            pc.setParent("com.spacemall.web");
        }
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("entity");
        pc.setXml("pojo");
        mpg.setPackageInfo(pc);

        if ("spacemall-web".equals(model)) {
            // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                    this.setMap(map);
                }
            };
            List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
            // 调整 xml 生成目录
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return path + "/src/main/resources/mybatis/mappers/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
        }

        TemplateConfig tc = new TemplateConfig();
        if ("spacemall-web".equals(model)) {
            tc.setEntity(null);
            tc.setXml(null);
        } else if ("spacemall-model".equals(model)) {
            tc.setController(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setMapper(null);
            tc.setXml(null);
        }
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

    }
}


