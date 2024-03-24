package com.jmp.ms.ecommerce.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {
	
	private Long id;
	
	@NotNull(message = "El email no puede estar en blanco")
	@Email(message = " El email no cumple con una estructura valida")
	private String email;
	
	@NotNull(message = "El nombre no puede estar en blanco")
	@Size(min = 2, message = "El nombre debe tener al menos 3 caracteres")
	private String nombres;
	
	@NotNull(message = "El apellido no puede estar en blanco")
	@Size(min = 2, message = "El apellido debe tener al menos 3 caracteres")
	private String apellidos;
	
	@NotNull(message = "La direccion no puede estar en blanco")
	@Size(min = 2, message = "La direccion debe tener al menos 3 caracteres")
	private String direccion;
	
	@NotNull(message = "El DNI no puede estar en blanco")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
	@Pattern(regexp = "\\d+", message = "El DNI debe contener solo dígitos")
	private String dni;
	
	@NotNull(message = "El telefono celular no puede estar en blanco")
	@Size(min = 9, max = 9, message = "El telefono celular debe tener 9 digitos")
	@Pattern(regexp = "\\d+", message = "El telefono celular debe contener solo dígitos")
	private String telefono;
	
}
