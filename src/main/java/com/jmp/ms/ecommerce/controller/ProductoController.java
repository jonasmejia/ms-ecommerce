package com.jmp.ms.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

	@Autowired
	private ProductoService prodService;
	
	@GetMapping
	public List<Producto> listarProductos(){
		return prodService.listartodos();
	}
	
	@GetMapping("/{id}")
	public Producto listarPorId(@PathVariable Long id) {
			return prodService.buscarPorId(id);
	}
	
	@PostMapping
	public Producto crearProducto(@RequestBody Producto producto)  { //throws IllegalOperationException
		//Producto crearProducto(@RequestBody Producto producto);
		return prodService.grabar(producto);
	}
	
	@PutMapping("/{id}")
	//public ResponseEntity<?> editarInvestigador(@PathVariable Long id, @RequestBody Investigador investigador) throws IllegalOperationException{
	public Producto editarProducto(@PathVariable Long id, @RequestBody Producto producto) {

		Producto invBD=prodService.buscarPorId(id);
		invBD.setNombre(producto.getNombre());
		invBD.setDescripcion(producto.getDescripcion());
		invBD.setPrecio(producto.getPrecio());
		invBD.setStock(producto.getStock());
		return prodService.actualizar(producto, id);
		
	}
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<?> eliminarInvestigador(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	public void eliminarProducto(@PathVariable Long id) {
		prodService.eliminar(id);
	
	}
}
