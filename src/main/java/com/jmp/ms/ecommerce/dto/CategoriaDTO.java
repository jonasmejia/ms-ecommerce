package com.jmp.ms.ecommerce.dto;

import java.util.List;



import com.jmp.ms.ecommerce.domain.Producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {

	private Long id;
	@Pattern(regexp = "^[A-Z]*$", message = "Los estados deben estar en mayuscula")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(min = 2, message = "El nombre debe tener al menos 3 caracteres")
	private String nombre;
	private String descripcion;
	private List<Producto> productos;
}
