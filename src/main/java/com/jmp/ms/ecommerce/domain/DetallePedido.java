package com.jmp.ms.ecommerce.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DetallePedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int cantidad;
	private double precio;
	@ManyToOne()
	private Pedido pedido;
	@ManyToOne()
	private Producto producto;
}
