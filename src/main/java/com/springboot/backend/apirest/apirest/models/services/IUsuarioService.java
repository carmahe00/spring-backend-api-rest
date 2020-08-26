package com.springboot.backend.apirest.apirest.models.services;

import com.springboot.backend.apirest.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
