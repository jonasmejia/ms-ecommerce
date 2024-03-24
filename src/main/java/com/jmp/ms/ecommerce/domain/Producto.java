package com.jmp.ms.ecommerce.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private EstadoProducto estado;
	@ManyToOne()
	private Categoria categoria;
	@OneToMany(mappedBy = "producto")
	private List<DetallePedido> detallePedido = new ArrayList<>();
}
