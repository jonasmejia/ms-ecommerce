package com.jmp.ms.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.ms.ecommerce.domain.Cliente;
import com.jmp.ms.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	@Autowired
	private ClienteService cliService;
	
	@GetMapping
	public List<Cliente> listarCliente(){
		return cliService.listartodos();
	}
	
	@GetMapping("/{id}")
	public Cliente listarPorId(@PathVariable Long id) {
			return cliService.buscarPorId(id);
	}
	
	@PostMapping
	public Cliente crearCliente(@RequestBody Cliente cliente)  { //throws IllegalOperationException
		//Cliente crearCategoria(@RequestBody Cliente cliente);
		return cliService.grabar(cliente);
	}
	
	@PutMapping("/{id}")
	//public ResponseEntity<?> editarInvestigador(@PathVariable Long id, @RequestBody Investigador investigador) throws IllegalOperationException{
	public Cliente editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {

		Cliente invBD=cliService.buscarPorId(id);
		invBD.setDni(cliente.getDni());
		invBD.setNombres(cliente.getNombres());
		invBD.setApellidos(cliente.getApellidos());
		invBD.setCorreo(cliente.getCorreo());
		invBD.setTelefono(cliente.getTelefono());
		invBD.setDireccion(cliente.getDireccion());
		return cliService.actualizar(cliente, id);
		
	}
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<?> eliminarInvestigador(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	public void eliminarCliente(@PathVariable Long id) {
		cliService.eliminar(id);
	
	}
}
