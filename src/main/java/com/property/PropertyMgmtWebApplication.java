package com.property;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.property.service.IStorageService;

@SpringBootApplication
/*@EnableJpaRepositories(basePackages= {"com.property.*"})
@ComponentScan(basePackages= {"com.property.*"})
@EntityScan(basePackages= {"com.property.*"})*/
public class PropertyMgmtWebApplication implements CommandLineRunner{

	@Resource
	IStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(PropertyMgmtWebApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
