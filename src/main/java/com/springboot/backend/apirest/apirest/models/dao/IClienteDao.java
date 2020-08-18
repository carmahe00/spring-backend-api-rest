
package com.springboot.backend.apirest.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.backend.apirest.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.apirest.models.entity.Region;

/**
 * 
 * @author juan
 * JpaRepository extiende de PagingAndSortingRepository (se puede paginar)
 */
public interface IClienteDao extends JpaRepository<Cliente, Long>{

	/**
	 * consulat jpa para retornar todas las regiones
	 * @return devuelve todas las regiones
	 */
	@Query("from Region")
	public List<Region> findAllRegiones();
}
