package com.jmp.ms.ecommerce.service;

import java.util.List;

import com.jmp.ms.ecommerce.domain.Cliente;

public interface ClienteService {
	List<Cliente> listartodos();
	Cliente buscarPorId(Long id);
	Cliente grabar(Cliente cliente); //throws IllegalOperationException;
	Cliente actualizar(Cliente cliente, long id);// throws EntityNotFoundException, IllegalOperationException;
	void eliminar(Long id);//  throws EntityNotFoundException, IllegalOperationException;
	Cliente findByDni(String dni);
	Cliente findByNombres(String nombres);
	Cliente findByApellidos(String apellidos);
}
