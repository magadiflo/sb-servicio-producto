package com.magadiflo.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.magadiflo.productos.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
