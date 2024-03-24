package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private CategoriaRepository catRep;
	
	@Override
	@Transactional
	public List<Categoria> listartodos() {
		return catRep.findAll();
	}

	@Override
	@Transactional
	public Categoria buscarPorId(Long id) {
		Optional<Categoria> categoria=catRep.findById(id);
		return categoria.get();
	}

	@Override
	@Transactional
	public Categoria grabar(Categoria categoria) {
		return catRep.save(categoria);
	}

	@Override
	@Transactional
	public Categoria actualizar(Categoria categoria, long id) {
		categoria.setId(id);		
		return catRep.save(categoria);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		catRep.deleteById(id);
	}

	@Override
	@Transactional
	public Categoria findByNombre(String nombre) {
		Optional<Categoria> inv=catRep.findByNombre(nombre);
		return inv.get();
	}

}
