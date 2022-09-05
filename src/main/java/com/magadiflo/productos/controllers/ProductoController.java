package com.magadiflo.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.commons.models.entity.Producto;
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
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return this.productoService.save(producto);		
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoBD = this.productoService.findById(id);
		productoBD.setNombre(producto.getNombre());
		productoBD.setPrecio(producto.getPrecio());
		return this.productoService.save(productoBD);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		this.productoService.deleteById(id);		
	}
	

}
