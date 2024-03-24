package com.jmp.ms.ecommerce.service;

import java.util.List;

import com.jmp.ms.ecommerce.domain.Producto;

public interface ProductoService {
	List<Producto> listartodos();
	Producto buscarPorId(Long id);
	Producto grabar(Producto producto); //throws IllegalOperationException;
	Producto actualizar(Producto producto, long id);// throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id);//  throws EntityNotFoundException, IllegalOperationException;
	Producto findByNombre(String nombre);
	Producto findByDescripcion(String descripcion);
}
