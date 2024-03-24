package com.jmp.ms.ecommerce.exception;

public class EntityNotFoundExceptionMessages {
	public static final String CATEGORIA_NOT_FOUND = "La cateogria con id proporcionado no fue encontrado";
	public static final String PRODUCTO_NOT_FOUND = "El producto con id proporcionado no fue encontrado";
	public static final String CLIENTE_NOT_FOUND = "El cliente con id proporcionado no fue encontrado";
	public static final String PEDIDO_NOT_FOUND = "El pedido con id proporcionado no fue encontrado";

    // Constructor privado para evitar instanciación
	private EntityNotFoundExceptionMessages() {
		throw new IllegalStateException ("Utility class");
	}
}
