package com.jmp.ms.ecommerce.service;

import java.util.List;

import com.jmp.ms.ecommerce.domain.Categoria;

public interface CategoriaService {
	List<Categoria> listartodos();
	Categoria buscarPorId(Long id);
	Categoria grabar(Categoria categoria); //throws IllegalOperationException;
	Categoria actualizar(Categoria categoria, long id);// throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id);//  throws EntityNotFoundException, IllegalOperationException;
	Categoria findByNombre(String nombre);
}
