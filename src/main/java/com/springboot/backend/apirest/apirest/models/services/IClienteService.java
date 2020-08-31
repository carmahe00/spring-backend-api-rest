package com.springboot.backend.apirest.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.backend.apirest.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.apirest.models.entity.Factura;
import com.springboot.backend.apirest.apirest.models.entity.Producto;
import com.springboot.backend.apirest.apirest.models.entity.Region;

public interface IClienteService {

	List<Cliente> fidnAll();
	
	Page<Cliente> findAll(Pageable pageable);
	
	Cliente findbyId(Long id);
	
	Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();
	
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Producto> findProductoByNombre(String term);
}
