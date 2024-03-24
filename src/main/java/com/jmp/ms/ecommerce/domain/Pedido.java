package com.jmp.ms.ecommerce.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date fecha_pedido;
	private double total;
	private EstadoPedido estado;
	@ManyToOne()
	private Cliente cliente;
	@OneToMany(mappedBy = "pedido")
	private List<DetallePedido> detallePedido = new ArrayList<>();
}
