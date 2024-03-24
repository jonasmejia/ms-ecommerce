package com.jmp.ms.ecommerce.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmp.ms.ecommerce.domain.Cliente;
import com.jmp.ms.ecommerce.domain.Pedido;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.EntityNotFoundExceptionMessages;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
  
	
	@Override
	@Transactional
	public List<Cliente> listarTodos() {
		return cliRep.findAll();
	}

	@Override
	@Transactional
	public Cliente buscarPorId(Long id) throws EntityNotFoundException {
		//Optional<Cliente> cliente=cliRep.findById(id);
		//return cliente.get();
		Optional<Cliente> cliente=cliRep.findById(id);
		if(cliente.isEmpty()) throw new EntityNotFoundException(EntityNotFoundExceptionMessages.CLIENTE_NOT_FOUND);
		return cliente.get();
	}

	@Override
	@Transactional
	public Cliente grabar(Cliente cliente) throws IllegalOperationException{
		//return cliRep.save(cliente);
		if(!cliRep.findByDni(cliente.getDni()).isEmpty()) {
			throw new IllegalOperationException("Ya existe un investigador con ese email");
		}
		if(!cliRep.findByDni(cliente.getDni()).isEmpty()) {
			throw new IllegalOperationException("Ya existe un investigador con ese dni");
		}
		return cliRep.save(cliente);
	}

	@Override
	@Transactional
	public Cliente actualizar(Cliente cliente, Long id) throws EntityNotFoundException, IllegalOperationException {
		Optional<Cliente> invEntity = cliRep.findById(id);
		//Validar si el cliente existe o no en la bd
		if(!invEntity.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.CLIENTE_NOT_FOUND);
	
	    // Validar si ya existe un cliente con el mismo DNI
	    Optional<Cliente> existingByDni = cliRep.findByDni(cliente.getDni());
	    if (existingByDni.isPresent() && !existingByDni.get().getId().equals(id)) {
	        throw new IllegalOperationException("Ya existe un cliente con ese dni");
	    }
				
		cliente.setId(id);		
		return cliRep.save(cliente);
	}

	@Override
	@Transactional
	public void eliminar(Long cliId) throws EntityNotFoundException, IllegalOperationException {
		cliRep.deleteById(cliId);
		
		/*Cliente cliEntity = cliRep.findById(cliId).orElseThrow(
				()->new EntityNotFoundException(EntityNotFoundExceptionMessages.CLIENTE_NOT_FOUND)
				);			
		List<Pedido> pedidos = pedRep.findByResponsable(cliEntity);
		if (!pedidos.isEmpty()) {
			throw new IllegalOperationException("El cliente es responsable de uno o mas pedidos");
		} 
		if(cliEntity.getPedidos()!=null) {
			throw new IllegalOperationException("El cliente tiene pedidos realizados");
		}
		cliRep.deleteById(cliId);
		*/
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
	
	@Override
	@Transactional
	public Cliente actualizarPorAtributos(Long id, Map<String, Object> camposActualizados)
			throws EntityNotFoundException, IllegalOperationException {
		Cliente cliente = cliRep.findById(id).
				orElseThrow(()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.CLIENTE_NOT_FOUND));
		Optional<Cliente> existingByDni = cliRep.findByDni(cliente.getDni());
	    if (existingByDni.isPresent() && !existingByDni.get().getId().equals(id)) {
	        throw new IllegalOperationException("Ya existe un investigador con ese dni");
	    }
	    
	    
		camposActualizados.forEach((campo, valor) -> {
            switch (campo) {
            	case "nombres":
            		cliente.setNombres((String) valor);
            		break;
            	case "apellidos":
            		cliente.setApellidos((String) valor);
            		break;
                case "dni":
                	cliente.setDni((String) valor);
                    break;          		
                case "correo":
                	cliente.setCorreo((String) valor);
                    break;
                case "direccion":
                	cliente.setDireccion((String) valor);
                    break;
                case "telefono":
                	cliente.setTelefono((String) valor);
                    break;
                default:
                    // No se hace nada para otros campos no reconocidos
                    break;
            }
        });
	
		
		return cliRep.save(cliente);
		
		
	}

}
