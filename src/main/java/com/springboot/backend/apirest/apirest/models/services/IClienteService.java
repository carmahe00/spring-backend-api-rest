package com.springboot.backend.apirest.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.backend.apirest.apirest.models.entity.Cliente;

public interface IClienteService {

	List<Cliente> fidnAll();
	
	Page<Cliente> findAll(Pageable pageable);
	
	Cliente findbyId(Long id);
	
	Cliente save(Cliente cliente);
	
	public void delete(Long id);
}
