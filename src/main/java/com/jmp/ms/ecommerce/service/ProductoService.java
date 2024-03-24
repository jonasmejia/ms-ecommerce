package com.jmp.ms.ecommerce.service;

import java.util.List;

import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;

public interface ProductoService {
	
	List<Producto> listarTodos();
	
	Producto buscarPorId(Long id);
	Producto grabar(Producto producto) throws IllegalOperationException;
	Producto actualizar(Producto producto, Long id) throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id)  throws EntityNotFoundException, IllegalOperationException;
	Producto findByNombre(String nombre);
	Producto findByDescripcion(String descripcion);
	public Producto asignarCategoria (Long idPro, Long idCat) throws EntityNotFoundException, IllegalOperationException;
	
	
}
