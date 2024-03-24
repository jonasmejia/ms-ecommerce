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

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.service.CategoriaService;


@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService catService;
	
	@GetMapping
	public List<Categoria> listarCategorias(){
		return catService.listartodos();
	}
	
	@GetMapping("/{id}")
	public Categoria listarPorId(@PathVariable Long id) {
			return catService.buscarPorId(id);
	}
	
	@PostMapping
	public Categoria crearCategoria(@RequestBody Categoria categoria)  { //throws IllegalOperationException
		//Categoria crearCategoria(@RequestBody Categoria categoria);
		return catService.grabar(categoria);
	}
	
	@PutMapping("/{id}")
	//public ResponseEntity<?> editarInvestigador(@PathVariable Long id, @RequestBody Investigador investigador) throws IllegalOperationException{
	public Categoria editarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {

		Categoria invBD=catService.buscarPorId(id);
		invBD.setNombre(categoria.getNombre());
		invBD.setDescripcion(categoria.getDescripcion());
		return catService.actualizar(categoria, id);
		
	}
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<?> eliminarInvestigador(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	public void eliminarCategoria(@PathVariable Long id) {
		catService.eliminar(id);
	
	}
}
