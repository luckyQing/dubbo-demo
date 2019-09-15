package com.liyulin.dubbo.demo.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.liyulin.dubbo.demo")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}