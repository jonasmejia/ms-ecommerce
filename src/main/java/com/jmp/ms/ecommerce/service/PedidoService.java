package com.jmp.ms.ecommerce.service;

import java.util.List;

import com.jmp.ms.ecommerce.domain.Pedido;

public interface PedidoService {
	List<Pedido> listartodos();
	Pedido buscarPorId(Long id);
	Pedido grabar(Pedido pedido);// throws IllegalOperationException;
	Pedido actualizar(Pedido pedido, long id);// throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id);// throws EntityNotFoundException, IllegalOperationException;
	
}
