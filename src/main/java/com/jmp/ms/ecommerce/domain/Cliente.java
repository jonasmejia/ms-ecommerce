package com.jmp.ms.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String dni;
	private String nombres;
	private String apellidos;
	private String correo;
	private String telefono;
	private String direccion;
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedido = new ArrayList<>();;
	
}
