package com.jmp.ms.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

	@Autowired
	private ProductoService prodService;
	
	@GetMapping
	public List<Producto> listarProductos(){
		return prodService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  listarPorId(@PathVariable Long id) {
			//return prodService.buscarPorId(id);
		Optional<Producto> invOptional = Optional.ofNullable(prodService.buscarPorId(id));
		if(invOptional.isPresent()) {
			return ResponseEntity.ok(invOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?>  crearProducto(@RequestBody Producto producto) throws IllegalOperationException  { //throws IllegalOperationException
		//Producto crearProducto(@RequestBody Producto producto);
		//return prodService.grabar(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(prodService.grabar(producto));
	}
	
	@PutMapping("/{id}")
	//public ResponseEntity<?> editarInvestigador(@PathVariable Long id, @RequestBody Investigador investigador) throws IllegalOperationException{
	public ResponseEntity<?>  editarProducto(@PathVariable Long id, @RequestBody Producto producto) throws IllegalOperationException {

		/*
		Producto invBD=prodService.buscarPorId(id);
		invBD.setNombre(producto.getNombre());
		invBD.setDescripcion(producto.getDescripcion());
		invBD.setPrecio(producto.getPrecio());
		invBD.setStock(producto.getStock());
		return prodService.actualizar(producto, id);
		*/
		Optional<Producto> o = Optional.ofNullable(prodService.buscarPorId(id));
		if (o.isPresent()) {
			Producto invBD = o.get();
			invBD.setNombre(producto.getNombre());
			invBD.setDescripcion(producto.getDescripcion());
			invBD.setPrecio(producto.getPrecio());
			invBD.setStock(producto.getStock());
						
			return ResponseEntity.status(HttpStatus.CREATED).body(prodService.grabar(invBD));
			
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<?> eliminarInvestigador(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		//prodService.eliminar(id);
		Optional<Producto> o = Optional.of(prodService.buscarPorId(id));
		if (o.isPresent()) {
			prodService.eliminar(id);
			return ResponseEntity.noContent().build();
			
		}
		return ResponseEntity.notFound().build();
		}
	
	}

