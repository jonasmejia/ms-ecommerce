package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;

public interface CategoriaService {
	
	List<Categoria> listarTodos();
	
	Categoria buscarPorId(Long id);
	Categoria grabar(Categoria categoria) throws IllegalOperationException;
	Categoria actualizar(Categoria categoria, Long id) throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	Categoria findByNombre(String nombre);
	
	/**
	 * @param id
	 * @param categoria
	 */
	Categoria actualizarPorAtributos(Long id, Map<String, Object> camposActualizados)
			throws EntityNotFoundException, IllegalOperationException ;

	
}
