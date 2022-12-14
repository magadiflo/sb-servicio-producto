package com.magadiflo.productos.models.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magadiflo.productos.models.dao.IProductoDao;
import com.magadiflo.commons.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	private final IProductoDao productoDao;

	public ProductoServiceImpl(IProductoDao productoDao) {
		this.productoDao = productoDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) this.productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return this.productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return this.productoDao.save(producto); //Nos permitirá crear (si id = null) o editar (si id = algún valor que exista) un producto
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.productoDao.deleteById(id);
	}

}
