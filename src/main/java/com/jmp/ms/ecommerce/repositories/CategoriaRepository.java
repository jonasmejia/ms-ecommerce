package com.jmp.ms.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.ms.ecommerce.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNombre(String nombre);
}
