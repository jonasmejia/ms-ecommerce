/**
 * @file: ErrorMessage.java
 * @author: (c)2024 evalencia 
 * @created: Feb 15, 2024 1:42:34 PM
 */
package com.jmp.ms.ecommerce.exception;

/**
 * Clase de utilidad que contiene mensajes de error para excepciones de tipo EntityNotFoundException.
 */
public final class EntityNotFoundExceptionMessages {

	public static final String CATEGORIA_NOT_FOUND = "La CATEGORIA con id proporcionado no fue encontrado";
	public static final String PRODUCTO_NOT_FOUND = "El PRODUCTO con id proporcionado no fue encontrado";
	public static final String CLIENTE_NOT_FOUND = "El CLIENTE con id proporcionado no fue encontrado";
	public static final String PEDIDO_NOT_FOUND = "El PEDIDO con id proporcionado no fue encontrado";
	
	
    // Constructor privado para evitar instanciación
	private EntityNotFoundExceptionMessages() {
		throw new IllegalStateException ("Utility class");
	}
}
