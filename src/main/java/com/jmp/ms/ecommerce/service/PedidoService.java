package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.jmp.ms.ecommerce.domain.Pedido;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;

public interface PedidoService {
	
	List<Pedido> listarTodos();
	
	Pedido buscarPorId(Long id);
	Pedido grabar(Pedido pedido) throws IllegalOperationException;
	Pedido actualizar(Pedido pedido, long id) throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
		
	/**
	 * @param id
	 * @param pedido
	 */
	
}
