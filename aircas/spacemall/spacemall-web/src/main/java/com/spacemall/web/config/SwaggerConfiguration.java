/***********************************************************************
 * SwaggerConfiguration.model H3C所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 *
 * @copyright Copyright: 2015-2020
 * @creator likewei<li.kewei       @       h3c.com>
 * @create-time 2018/4/4 16:18
 * @revision $Id: *
 ***********************************************************************/
package com.spacemall.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spacemall.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo")
                .description("demo")
                .termsOfServiceUrl("http:127.0.0.1:8888/swagger-ui.html")
                .version("0.0.1")
                .build();
    }

}
