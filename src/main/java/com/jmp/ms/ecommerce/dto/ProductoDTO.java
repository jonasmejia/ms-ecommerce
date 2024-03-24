package com.jmp.ms.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductoDTO {
	
	private Long id;
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(min = 2, message = "El nombre debe tener al menos 3 caracteres")
	private String nombres;
	
	@NotBlank(message = "La descripcion no puede estar en blanco")
	@Size(min = 2, message = "La descripcion debe tener al menos 3 caracteres")
	private String descripcion;
	
	@NotBlank(message = "El Estado no puede estar en blanco")
    @Size(min = 0, max = 1, message = "El estado debe tener exactamente 1 caracter")
	@Pattern(regexp = "\\d+", message = "El estado debe contener solo dígitos")
	private String estado;
	
	@NotBlank(message = "El precio no puede estar en blanco")
	@Size(min = 0,  message = "El precio debe ser mayor a 0")
	private double precio;
	
	@NotBlank(message = "El stock no puede estar en blanco")
	@Size(min = 0,  message = "El stock debe ser mayor a 0")
	private double stock;
	
}
