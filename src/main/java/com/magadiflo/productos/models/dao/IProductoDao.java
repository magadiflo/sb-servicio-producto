package com.magadiflo.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.magadiflo.productos.models.entity.Producto;

/**
 * CrudRepository, de por sí, es un componente manejado por Spring, eso nos
 * permitirá hacer uso de la inyección de dependencia. En este caso, nuestra
 * interfaz IProductoDao hereda de CrudRepository, eso significa que
 * IProductoDao (por defecto) ya puede ser usado con inyección de dependencia.
 * Un ejemplo podemos ver en la clase ProductoServiceImpl, donde hacemos la
 * inyección de dependencia vía constructor.
 * 
 * En el tutorial se hace uso de la anotación @Autowire para hacer la inyección
 * de dependencia, pero según leí, no es una buena práctica hacer uso de él, es
 * mejor usar la inyección de dependencia vía constructor.
 * 
 * Tampoco sería necesario hacer uso de anotaciones como @Component
 * o @Repository, ya que se registra de forma automátic por debajo al estar
 * extendiendo de CrudRepository
 *
 */
public interface IProductoDao extends CrudRepository<Producto, Long> {

}
