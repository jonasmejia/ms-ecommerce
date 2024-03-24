package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.jmp.ms.ecommerce.domain.Cliente;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;

public interface ClienteService {
	
	List<Cliente> listarTodos();
	
	Cliente buscarPorId(Long id);
	Cliente grabar(Cliente cliente) throws IllegalOperationException;
	Cliente actualizar(Cliente cliente, Long id) throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException;
	Cliente findByDni(String dni);
	Cliente findByNombres(String nombres);
	Cliente findByApellidos(String apellidos);
	
	/**
	 * @param id
	 * @param cliente
	 */
	Cliente actualizarPorAtributos(Long id, Map<String, Object> camposActualizados)
			throws EntityNotFoundException, IllegalOperationException ;
}
