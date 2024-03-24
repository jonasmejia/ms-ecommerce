package com.jmp.ms.ecommerce.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.dto.CategoriaDTO;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.service.CategoriaService;
import com.jmp.ms.ecommerce.util.ApiResponse;

import jakarta.validation.Valid;
/**
 * Se utiliza version en cabecera, utilizar Key = X-VERSION y Value 1.0.0
 */

@RestController
@RequestMapping(value = "/api/v1/categorias") //, headers ="X-VERSION=1.0.0")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Categoria> categorias = catService.listarTodos();
    	if(categorias==null || categorias.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
    	else {     
    		List<CategoriaDTO> categoriaDTOs = categorias.stream()
    				.map(categoria -> modelMapper.map(categoria, CategoriaDTO.class))
    				.collect(Collectors.toList());
    		ApiResponse<List<CategoriaDTO>> response = new ApiResponse<>(true, "Lista de categorias obtenida con éxito", categoriaDTOs);
    		return ResponseEntity.ok(response);
    	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		//function Investiador
		//return catService.buscarPorId(id);
		Categoria categoria = catService.buscarPorId(id);
		CategoriaDTO categoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
    	ApiResponse<CategoriaDTO> response = new ApiResponse<>(true, "Datos de la categoria obtenidos con éxito", categoriaDTO);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody CategoriaDTO categoriaDTO) throws IllegalOperationException { 
	
		//Categoria crearCategoria(@RequestBody Categoria categoria);
		//return catService.grabar(categoria);
		//return ResponseEntity.status(HttpStatus.CREATED).body(catService.grabar(categoria));
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
    	catService.grabar(categoria);
    	CategoriaDTO savedCategoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
    	ApiResponse<CategoriaDTO> response = new ApiResponse<>(true, "Datos del Investigador grabados con éxito", savedCategoriaDTO);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {       
  	
    	Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
    	catService.actualizar(categoria, id);
    	CategoriaDTO updatedCategoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
    	ApiResponse<CategoriaDTO> response = new ApiResponse<>(true, "Datos de la categoria actualizados con éxito", updatedCategoriaDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
    @PatchMapping("/{id}")
	public ResponseEntity<?> actualizarParcial(@Valid @RequestBody Map<String, Object> camposActualizados, BindingResult bindingResult  ,@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
    	
    	// Validación manual
        bindingResult = new BeanPropertyBindingResult(camposActualizados, "camposActualizados");
              
        // Validar que el nombre no este en blanco
        if (camposActualizados.containsKey("nombre")) {
            String nombre = (String) camposActualizados.get("nombre");
            if (!isValidString(nombre)) {
            	throw new IllegalOperationException ("El atributo nombres no debe estar en blanco");
            }
        }
        
        // Validar que el apepat no este en blanco
        if (camposActualizados.containsKey("descripcion")) {
            String descripcion = (String) camposActualizados.get("descripcion");
            if (!isValidString(descripcion)) {
            	throw new IllegalOperationException ("La descripcion no debe estar en blanco");
            }
        }  
        

        if (bindingResult.hasErrors()) {
            // Manejar los errores de validación, por ejemplo, devolver una respuesta con los errores encontrados
            return ResponseEntity.badRequest().build();
        }
    	
    	
    	//Despues de las validaciones realizamos la actualizacion
    	
    	Categoria categoria = catService.actualizarPorAtributos(id,camposActualizados);   	
    	
    	CategoriaDTO updatedCategoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);
    	ApiResponse<CategoriaDTO> response = new ApiResponse<>(true, "Datos de la categoria actualizados con éxito", updatedCategoriaDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
	
	@DeleteMapping("/{id}")
	//public ResponseEntity<?> eliminarInvestigador(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		catService.eliminar(id);
    	ApiResponse<?> response = new ApiResponse<>(true, "Categoria eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);//NO_CONTENT
	
	}
	
	// Método para validar que una cadena no este en blanco
    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
}
