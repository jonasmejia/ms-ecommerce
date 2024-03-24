package com.jmp.ms.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.ms.ecommerce.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByDni(String dni);
	Optional<Cliente> findByNombres(String nombres);
	Optional<Cliente> findByApellidos(String apellidos);
	
}
