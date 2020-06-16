package com.example.demo.config;

import com.example.demo.controller.JerseyModelController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig() {
        register(JerseyModelController.class);
    }

    @PostConstruct
    private void init() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.configSwagger();
    }

    private void configSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("springboot_jersey_demo");
        beanConfig.setVersion("0.0.1");
        beanConfig.setContact("http://localhost:8888/jersey/swagger.json");
        beanConfig.setBasePath("/jersey");
        beanConfig.setResourcePackage("com.example.demo.controller");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }


}
