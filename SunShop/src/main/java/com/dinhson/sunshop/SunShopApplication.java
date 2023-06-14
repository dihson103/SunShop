package com.dinhson.sunshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dinhson.sunshop"})
public class SunShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunShopApplication.class, args);
	}

}
