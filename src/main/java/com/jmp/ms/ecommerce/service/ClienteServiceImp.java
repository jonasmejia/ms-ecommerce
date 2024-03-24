package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jmp.ms.ecommerce.domain.Cliente;
import com.jmp.ms.ecommerce.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired
	private ClienteRepository cliRep;
	
	@Override
	@Transactional
	public List<Cliente> listartodos() {
		return cliRep.findAll();
	}

	@Override
	@Transactional
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente=cliRep.findById(id);
		return cliente.get();
	}

	@Override
	@Transactional
	public Cliente grabar(Cliente cliente) {
		return cliRep.save(cliente);
	}

	@Override
	@Transactional
	public Cliente actualizar(Cliente cliente, long id) {
		cliente.setId(id);		
		return cliRep.save(cliente);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		cliRep.deleteById(id);
	}

	@Override
	@Transactional
	public Cliente findByDni(String dni) {
		Optional<Cliente> inv=cliRep.findByDni(dni);
		return inv.get();
	}

	@Override
	@Transactional
	public Cliente findByNombres(String nombres) {
		Optional<Cliente> inv=cliRep.findByNombres(nombres);
		return inv.get();
	}

	@Override
	@Transactional
	public Cliente findByApellidos(String apellidos) {
		Optional<Cliente> inv=cliRep.findByApellidos(apellidos);
		return inv.get();
	}

}
