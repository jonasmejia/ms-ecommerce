package com.jmp.ms.ecommerce.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PedidoDTO {

	private long id;
	
	@NotNull(message = "El Estado no puede estar en blanco")
    @Size(min = 0, max = 1, message = "El estado debe tener exactamente 1 caracter")
	@Pattern(regexp = "\\d+", message = "El estado debe contener solo dígitos")
	private String estado;
	
	@NotNull(message = "La fecha de registro de investigación no puede estar vacía")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaRegInv;
	
	@NotNull(message = "El total no puede estar en blanco")
	private double total;
}
