package com.magadiflo.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * En este servicio de productos se usa la anotación @EntityScan. 
 * Es para registrar el package donde tenemos clases de hibernate/jpa, pero en un contexto de persistencia.
 * 
 * Nuestro servicio items no maneja persistencia jpa, solo usamos esas clases como POJO pero no como clase entity, 
 * por eso no es necesario el @EntityScan. 
 *  
 * Pero si tuviéramos un servicio con persistencia y necesitamos esa clases para trabajar con crud repository, 
 * jpa e hibernate ahí si que es necesaria.
 *
 */

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.magadiflo.commons.models.entity"}) //Le decimos que las clases del package entity las busque o las escanee en en el proyecto de commons ya que en nuestro servicio productos se maneja persistencia de la clase Producto
public class SbServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServicioProductosApplication.class, args);
	}

}
