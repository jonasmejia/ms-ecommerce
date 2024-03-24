package com.jmp.ms.ecommerce.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.ms.ecommerce.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
