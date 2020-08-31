package com.springboot.backend.apirest.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.apirest.apirest.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

	
}
