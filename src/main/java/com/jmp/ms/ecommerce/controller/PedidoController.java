package com.jmp.ms.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.ms.ecommerce.domain.Pedido;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedService;
	
	@GetMapping
	public List<Pedido> listarPedidos(){
		return pedService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Pedido listarPorId(@PathVariable Long id) {
			return pedService.buscarPorId(id);
	}
	
	@PostMapping
	public Pedido crearPedido(@RequestBody Pedido pedido)  throws IllegalOperationException {
		//Categoria crearCategoria(@RequestBody Categoria categoria);
		return pedService.grabar(pedido);
	}
	
	@PutMapping("/{id}")
	public Pedido editarPedido(@PathVariable Long id, @RequestBody Pedido pedido) throws IllegalOperationException{
		//public Pedido editarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {

		Pedido invBD=pedService.buscarPorId(id);
		invBD.setFecha_pedido(pedido.getFecha_pedido());
		invBD.setTotal(pedido.getTotal());
		invBD.setEstado(pedido.getEstado());
		return pedService.actualizar(pedido, id);
		
	}
	
	@DeleteMapping("/{id}")
	public void eliminarPedido(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	//public void eliminarPedido(@PathVariable Long id) {
		pedService.eliminar(id);
	
	}
}
