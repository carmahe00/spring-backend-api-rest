package com.springboot.backend.apirest.apirest.models.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UserPrincipal implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	//private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	public UserPrincipal(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = usuario.getRoles(); 
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role rol : roles) {
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return usuario.getEnabled();
	}

}
