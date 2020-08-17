
package com.springboot.backend.apirest.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.apirest.apirest.models.entity.Cliente;

/**
 * 
 * @author juan
 * JpaRepository extiende de PagingAndSortingRepository (se puede paginar)
 */
public interface IClienteDao extends JpaRepository<Cliente, Long>{

}
