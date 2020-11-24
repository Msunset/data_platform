package com.xiangban.data_platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("com.xiangban.data_platform.mapper")
@EnableSwagger2
public class DataPlatformApplication {


	public static void main(String[] args) {
		SpringApplication.run(DataPlatformApplication.class, args);
	}


}
