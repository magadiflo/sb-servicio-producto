package com.magadiflo.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.productos.models.entity.Producto;
import com.magadiflo.productos.models.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private Environment env; // local.server.port, solo sería para cuando el puerto está en automático
	
	@Value("${server.port}") // Toma el puerto en establecido en el application properties, por defecto lo dejamos en cero
	private Integer port;

	private final IProductoService productoService;

	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public List<Producto> listar() {
		return this.productoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//producto.setPort(this.port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id) throws InterruptedException {
		
		//-- Simulando un error
		if(id.equals(10L)) {
			throw new IllegalStateException("Producto no encontrado");			
		}
		
		//-- Simulando retardo de 5 segundos
		if(id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		//--
		
		Producto producto = this.productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port"))); //local, prefijo que se le agrega, server.port el puerto que queremos obtener. Tomará el puerto real y no el cero definido
		//producto.setPort(this.port);
		return producto;
	}

}
