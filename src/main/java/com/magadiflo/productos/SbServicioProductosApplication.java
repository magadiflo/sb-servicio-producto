package com.magadiflo.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.magadiflo.commons.models.entity"}) //Le decimos que las clases del package entity las busque o las escanee en en el proyecto de commons
public class SbServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServicioProductosApplication.class, args);
	}

}
