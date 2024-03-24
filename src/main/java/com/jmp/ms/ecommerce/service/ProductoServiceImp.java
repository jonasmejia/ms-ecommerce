package com.jmp.ms.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.ms.ecommerce.domain.Categoria;
import com.jmp.ms.ecommerce.domain.Producto;
import com.jmp.ms.ecommerce.exception.EntityNotFoundException;
import com.jmp.ms.ecommerce.exception.EntityNotFoundExceptionMessages;
import com.jmp.ms.ecommerce.exception.IllegalOperationException;
import com.jmp.ms.ecommerce.repositories.ProductoRepository;
import com.jmp.ms.ecommerce.repositories.CategoriaRepository;



@Service
public class ProductoServiceImp implements ProductoService {

	@Autowired
	private ProductoRepository prodRep;
	
	@Autowired
    private CategoriaRepository catRep;
	
	@Override
	@Transactional
	public List<Producto> listarTodos() {
		return prodRep.findAll();
	}

	@Override
	@Transactional
	public Producto buscarPorId(Long id) throws EntityNotFoundException {
		/*Optional<Producto> producto=prodRep.findById(id);
		return producto.get();*/
		Optional<Producto> producto=prodRep.findById(id);
		if(producto.isEmpty()) throw new EntityNotFoundException(EntityNotFoundExceptionMessages.PRODUCTO_NOT_FOUND);
		return producto.get();

	}

	@Override
	@Transactional
	public Producto grabar(Producto producto) throws IllegalOperationException {
		return prodRep.save(producto);
	}

	@Override
	@Transactional
	public Producto actualizar(Producto producto, Long id) throws IllegalOperationException {
		//producto.setId(id);		
		//return prodRep.save(producto);
		Optional<Producto> invEntity = prodRep.findById(id);
		//Validar si el proyecto existe o no en la bd
		if (invEntity.isPresent()) {
            throw new EntityNotFoundException(EntityNotFoundExceptionMessages.PRODUCTO_NOT_FOUND);
        }
        producto.setId(id);		
		return prodRep.save(producto);
	}

	@Override
	@Transactional
	public void eliminar(Long id) throws EntityNotFoundException, IllegalOperationException  {
		//prodRep.deleteById(id);
		prodRep.findById(id).orElseThrow(
				()->new EntityNotFoundException(EntityNotFoundExceptionMessages.PRODUCTO_NOT_FOUND)
				);					
		prodRep.deleteById(id);
	}

	@Override
	@Transactional
	public Producto findByNombre(String nombre) {
		Optional<Producto> inv=prodRep.findByNombre(nombre);
		return inv.get();
	}

	@Override
	@Transactional
	public Producto findByDescripcion(String descripcion) {
		Optional<Producto> inv=prodRep.findByDescripcion(descripcion);
		return inv.get();
	}
	
	@Override
	@Transactional
	public Producto asignarCategoria (Long idPro, Long idCat) throws EntityNotFoundException, IllegalOperationException {
		Producto proEntity = prodRep.findById(idPro)
				.orElseThrow(()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.PRODUCTO_NOT_FOUND));
		
		Categoria catEntity = catRep.findById(idCat)
				.orElseThrow(
						()-> new EntityNotFoundException(EntityNotFoundExceptionMessages.CATEGORIA_NOT_FOUND)
						);
			
		if (proEntity.getCategoria()!= null) {
			throw new IllegalOperationException("El producto ya tiene asignado una categoria");

        } 
		proEntity.setCategoria(catEntity);
		return prodRep.save(proEntity);
		
	}
	
}
