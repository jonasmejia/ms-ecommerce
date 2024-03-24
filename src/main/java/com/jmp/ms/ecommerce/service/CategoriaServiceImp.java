package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.repositories.CategoriaRepository;
import com.jmp.ms.ecommerce.repositories.ProductoRepository;
import com.jmp.ms.ecommerce.exception.EntityNotFoundExceptionMessages;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;


@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private ProductoRepository prodRep;
	
	@Override
	@Transactional
	public List<Categoria> listarTodos() {
		return catRep.findAll();
	}

	@Override
	@Transactional
	public Categoria buscarPorId(Long id) throws EntityNotFoundException {
		Optional<Categoria> categoria=catRep.findById(id);
		if(categoria.isEmpty()) throw new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND);
		return categoria.get();
	}

	@Override
	@Transactional
	public Categoria grabar(Categoria categoria) throws IllegalOperationException{
		if(!catRep.findByNombre(categoria.getNombre()).isEmpty()) {
			throw new IllegalOperationException("Ya existe una categoria con ese nombre");
		}
		//if(!categoria.getNombre().isEmpty()) {
		//	throw new IllegalOperationException("El nombre no puede ser vacio");
		//}
		
		return catRep.save(categoria);
	}

	@Override
	@Transactional
	public Categoria actualizar(Categoria categoria, Long id)  throws EntityNotFoundException, IllegalOperationException {
		//categoria.setId(id);		
		//return catRep.save(categoria);
		Optional<Categoria> invEntity = catRep.findById(id);
		
		//Validar si el investigador existe o no en la bd
		if(!invEntity.isPresent())
			throw new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND);
		
		// Validar si ya existe un investigador con el mismo email
	    Optional<Categoria> existingByNombre = catRep.findByNombre(categoria.getNombre());
	    if (existingByNombre.isPresent() && !existingByNombre.get().getId().equals(id) 
	    		&& !categoria.getNombre().equalsIgnoreCase(invEntity.get().getNombre())) {
	        throw new IllegalOperationException("Ya existe un investigador con ese email");
	    }
	    
		if (invEntity.isEmpty()) {
            throw new EntityNotFoundException("La categoria no existe");
        }
        if (!catRep.findByNombre(categoria.getNombre()).isEmpty()) {
            throw new IllegalOperationException("El nombre de la categoria ya existe");
        }
        
        categoria.setId(id);
        
		return catRep.save(categoria);
		
	}

	@Override
	@Transactional
	public void eliminar(Long id)throws EntityNotFoundException, IllegalOperationException {
		//catRep.deleteById(id);
		Categoria invEntity = catRep.findById(id).orElseThrow(
				()->new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND)
				);
		List<Producto> productos = prodRep.findByCategoria(invEntity);
		if (!productos.isEmpty()) {
			throw new IllegalOperationException("La categoria es responsable de uno o mas productos");
		} 
		//if(invEntity.getProductos()!=null) {
		//	throw new IllegalOperationException("La categoria tiene productos asignados");
		//}
		catRep.deleteById(id);
	}

	@Override
	@Transactional
	public Categoria findByNombre(String nombre) {
		//Optional<Categoria> inv=catRep.findByNombre(nombre);
		//return inv.get();
		Optional<Categoria> categoria = catRep.findByNombre(nombre);
		if(categoria.isEmpty())throw new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND);
		return categoria.get();
	}
	
	@Override
	@Transactional
	public Categoria actualizarPorAtributos(Long id, Map<String, Object> camposActualizados)
			throws EntityNotFoundException, IllegalOperationException {
		Categoria categoria = catRep.findById(id).
				orElseThrow(()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND));
		Optional<Categoria> existingByNombre = catRep.findByNombre(categoria.getNombre());
	    if (existingByNombre.isPresent() && !existingByNombre.get().getId().equals(id)) {
	        throw new IllegalOperationException("Ya existe una categoria con ese nombre");
	    }
	      
		camposActualizados.forEach((campo, valor) -> {
            switch (campo) {
            case "descripcion":
        		categoria.setDescripcion((String) valor);
        		break;
        	case "nombre":
        		categoria.setNombre((String) valor);
        		break;
        	
            default:
                // No se hace nada para otros campos no reconocidos
                break;
            }
        });
	
		
		return catRep.save(categoria);
		
		
	}

}
