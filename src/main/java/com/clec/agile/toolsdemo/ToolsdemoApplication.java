package com.clec.agile.toolsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.clec.agile.toolsdemo", "com.clec.agile.toolsdemo.controller" })
public class ToolsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsdemoApplication.class, args);
	}
}
