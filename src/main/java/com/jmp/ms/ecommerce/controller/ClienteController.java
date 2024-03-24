package com.jmp.ms.ecommerce.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
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

import com.jmp.ms.ecommerce.domain.Cliente;
import com.jmp.ms.ecommerce.dto.CategoriaDTO;
import com.jmp.ms.ecommerce.dto.ClienteDTO;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.service.ClienteService;
import com.jmp.ms.ecommerce.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService cliService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> listarClientes(){
		//return cliService.listarTodos();
		List<Cliente> clientes = cliService.listarTodos();
    	if(clientes==null || clientes.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}
    	else {     
    		List<ClienteDTO> clienteDTOs = clientes.stream()
    				.map(cliente -> modelMapper.map(clientes, ClienteDTO.class))
    				.collect(Collectors.toList());
    		ApiResponse<List<ClienteDTO>> response = new ApiResponse<>(true, "Lista de categorias obtenida con éxito", clienteDTOs);
    		return ResponseEntity.ok(response);
    	}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
			//return cliService.buscarPorId(id);
		Cliente cliente = cliService.buscarPorId(id);
		ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
    	ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Datos de la categoria obtenidos con éxito", clienteDTO);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody ClienteDTO clienteDTO) throws IllegalOperationException { 
		//Cliente crearCategoria(@RequestBody Cliente cliente);
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
    	cliService.grabar(cliente);
    	CategoriaDTO savedClienteDTO = modelMapper.map(cliente, CategoriaDTO.class);
    	ApiResponse<CategoriaDTO> response = new ApiResponse<>(true, "Datos del Investigador grabados con éxito", savedClienteDTO);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {       
	  	
    	Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
    	cliService.actualizar(cliente, id);
    	ClienteDTO updatedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
    	ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Datos de la categoria actualizados con éxito", updatedClienteDTO);
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	 @PatchMapping("/{id}")
		public ResponseEntity<?> actualizarParcial(@Valid @RequestBody Map<String, Object> camposActualizados, BindingResult bindingResult  ,@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException{
	    	
	    	// Validación manual
	        bindingResult = new BeanPropertyBindingResult(camposActualizados, "camposActualizados");
	         
	        // Validar el formato del correo electrónico
	        if (camposActualizados.containsKey("email")) {
	            String email = (String) camposActualizados.get("email");
	            if (!isValidEmail(email)) {
	                throw new IllegalOperationException ("El atributo email no es valido");            	
	            }
	        }

	        // Validar el formato del DNI
	        if (camposActualizados.containsKey("dni")) {
	            String dni = (String) camposActualizados.get("dni");
	            if (!isValidDni(dni)) {
	            	throw new IllegalOperationException ("El atributo DNI no es valido");
	            }
	        }
	        
	     // Validar el formato del DNI
	        if (camposActualizados.containsKey("telefono")) {
	            String telefono = (String) camposActualizados.get("telefono");
	            if (!isValidTelefono(telefono)) {
	            	throw new IllegalOperationException ("El atributo telefono no es valido");
	            }
	        }
	        
	        // Validar que el nombre no este en blanco
	        if (camposActualizados.containsKey("nombres")) {
	            String nombres = (String) camposActualizados.get("nombres");
	            if (!isValidString(nombres)) {
	            	throw new IllegalOperationException ("El atributo nombres no debe estar en blanco");
	            }
	        }
	        
	        // Validar que el nombre no este en blanco
	        if (camposActualizados.containsKey("apellidos")) {
	            String apellidos = (String) camposActualizados.get("apellidos");
	            if (!isValidString(apellidos)) {
	            	throw new IllegalOperationException ("El atributo apellidos no debe estar en blanco");
	            }
	        }
	       
	     // Validar que el nombre no este en blanco
	        if (camposActualizados.containsKey("direccion")) {
	            String direccion = (String) camposActualizados.get("direccion");
	            if (!isValidString(direccion)) {
	            	throw new IllegalOperationException ("El atributo direccion no debe estar en blanco");
	            }
	        }
	        
	     

	        if (bindingResult.hasErrors()) {
	            // Manejar los errores de validación, por ejemplo, devolver una respuesta con los errores encontrados
	            return ResponseEntity.badRequest().build();
	        }
	    	
	    	
	    	//Despues de las validaciones realizamos la actualizacion
	    	
	    	Cliente cliente = cliService.actualizarPorAtributos(id,camposActualizados);   	
	    	
	    	ClienteDTO updatedClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
	    	ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Datos de la categoria actualizados con éxito", updatedClienteDTO);
	    	return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
		cliService.eliminar(id);
    	ApiResponse<?> response = new ApiResponse<>(true, "Cliente eliminado con éxito", null);
    	return ResponseEntity.status(HttpStatus.OK).body(response);//NO_CONTENT
	
	}
	
	// Método para validar que una cadena no este en blanco
    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
		    
	// Método para validar el formato del correo electrónico
	private boolean isValidEmail(String email) {
	    // Utiliza una expresión regular para validar el formato del correo electrónico
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    return pattern.matcher(email).matches();
	}
	
	// Método para validar el formato del DNI
	private boolean isValidDni(String dni) {
	    // Utiliza una expresión regular para validar que el DNI contenga solo dígitos y tenga exactamente 8 caracteres
	    return dni != null && dni.matches("\\d{8}");
	    }
	    
	 // Método para validar el formato del DNI
	private boolean isValidTelefono(String telefono) {
	    // Utiliza una expresión regular para validar que el DNI contenga solo dígitos y tenga exactamente 8 caracteres
	    return telefono != null && telefono.matches("\\d{9}");
	}
    
}
