package com.springboot.backend.apirest.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.backend.apirest.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
