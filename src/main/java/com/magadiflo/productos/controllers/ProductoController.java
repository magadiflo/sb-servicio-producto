package com.magadiflo.productos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.productos.models.entity.Producto;
import com.magadiflo.productos.models.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private final IProductoService productoService;

	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public List<Producto> listar() {
		return this.productoService.findAll();
	}

	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id) {
		return this.productoService.findById(id);
	}

}
