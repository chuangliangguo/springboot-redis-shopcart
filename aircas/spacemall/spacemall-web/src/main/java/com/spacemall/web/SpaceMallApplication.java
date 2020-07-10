package com.spacemall.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableAsync
@EnableWebMvc
@EnableCaching
@ServletComponentScan
@MapperScan(basePackages = "com.spacemall.web.dao")
public class SpaceMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceMallApplication.class, args);
	}

}
