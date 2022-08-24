package com.magadiflo.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.productos.models.entity.Producto;
import com.magadiflo.productos.models.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	//@Autowired
	//private Environment env; // Lo usaremos para obtener el puerto
	
	@Value("${server.port}")
	private Integer port;

	private final IProductoService productoService;

	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public List<Producto> listar() {
		return this.productoService.findAll().stream().map(producto -> {
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(this.port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = this.productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port"))); //local, prefijo que se le agrega, server.port el puerto que queremos obtener
		producto.setPort(this.port);
		return producto;
	}

}