package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

	@Autowired
	private ProductoRepository prodRep;
	
	@Override
	@Transactional
	public List<Producto> listartodos() {
		return prodRep.findAll();
	}

	@Override
	@Transactional
	public Producto buscarPorId(Long id) {
		Optional<Producto> producto=prodRep.findById(id);
		return producto.get();
	}

	@Override
	@Transactional
	public Producto grabar(Producto producto) {
		return prodRep.save(producto);
	}

	@Override
	@Transactional
	public Producto actualizar(Producto producto, long id) {
		producto.setId(id);		
		return prodRep.save(producto);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		prodRep.deleteById(id);
	}

	@Override
	@Transactional
	public Producto findByNombre(String nombre) {
		Optional<Producto> inv=prodRep.findByNombre(nombre);
		return inv.get();
	}

	@Override
	@Transactional
	public Producto findByDescripcion(String descripcion) {
		Optional<Producto> inv=prodRep.findByDescripcion(descripcion);
		return inv.get();
	}

	
}
