package com.easingyou.funfactstore;

import org.springframework.boot.SpringApplication;

public class TestFunfactstoreApplication {

	public static void main(String[] args) {
		SpringApplication.from(FunFactStoreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
