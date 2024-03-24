package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jmp.ms.ecommerce.domain.Pedido;
import com.jmp.ms.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoServiceImp implements PedidoService {

	@Autowired
	private PedidoRepository pedRep;
	
	@Override
	public List<Pedido> listarTodos() {
		return pedRep.findAll();
	}

	@Override
	public Pedido buscarPorId(Long id) {
		Optional<Pedido> categoria=pedRep.findById(id);
		return categoria.get();
	}

	@Override
	public Pedido grabar(Pedido pedido) {
		return pedRep.save(pedido);
	}

	@Override
	public Pedido actualizar(Pedido pedido, long id) {
		pedido.setId(id);		
		return pedRep.save(pedido);
	}

	@Override
	public void eliminar(Long id) {
		pedRep.deleteById(id);

	}

}
