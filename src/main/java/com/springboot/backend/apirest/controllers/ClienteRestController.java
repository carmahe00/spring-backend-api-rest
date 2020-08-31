package com.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.backend.apirest.apirest.models.entity.Cliente;
import com.springboot.backend.apirest.apirest.models.entity.Region;
import com.springboot.backend.apirest.apirest.models.services.IClienteService;
import com.springboot.backend.apirest.apirest.models.services.IUploadFileService;

/**
 * @RestController es un controlador que devuelve respuestas a los clientes
 * @CrossOrigin permite conexión de origenes cruzados; también se puede aceptar
 *              métodos (POST, PUT, DELETE, GET)
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.fidnAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}

	/**
	 * método para buscar cliente
	 * 
	 * @param id       es el identificador del cliente
	 * @param response almacena los mensajes
	 * @exception DataAccessException indica si hay problema en base de datos
	 *                                (conexión, anotaciones ...etc) 500 si el
	 *                                cliente no existe en la base de datos,
	 *                                enviamos un mensaje de error 404
	 * @return devulve un ResponseEntity que almacena mensajes y objetos en nuestro
	 *         caso cualquiere cosa ResponseEntity<?>
	 */
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findbyId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en al base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	/**
	 * método para crear un clinte
	 * 
	 * @param cliente lo enviamos en formato json
	 * @RequestBody indica que recibimos en formato json
	 * @param result validamos si hay erroes, cuando haya errores creamos un List de
	 *               string convietiendo erroes en un stream, devolviendo un map de
	 *               string y por útimo convirtiendo a una lista
	 * @exception ResponseEntity indica que hay errores en la base de datos 500
	 * @return devulve un ResponseEntity que almacena mensajes y objetos en nuestro
	 *         caso cualquiere cosa ResponseEntity<?>
	 */
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.putIfAbsent("errors", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("cliente", clienteNew);
		response.put("mensaje", "Cliente creado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * método para actualizar
	 * 
	 * @param cliente es el cliente que recibimos
	 * @param result  validamos si hay erroes, cuando haya errores creamos un List
	 *                de string convietiendo erroes en un stream, devolviendo un map
	 *                de string y por útimo convirtiendo a una lista
	 * @param id      es el identificador del cliente
	 * @return devulve un ResponseEntity que almacena mensajes y objetos en nuestro
	 *         caso cualquiere cosa ResponseEntity<?>
	 */
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findbyId(id);
		Cliente clienteUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.putIfAbsent("errors", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se puede editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en al base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteUpdate = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente actualizado con éxito");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * método para eliminar
	 * 
	 * @param id es el identificador del cliente
	 */
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteService.findbyId(id);
			String nombreFotoAnterior = cliente.getFoto();
			uploadFileService.eliminar(nombreFotoAnterior);
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * método para subir la imagen
	 * 
	 * @param archivo es la imagen que envía desde el cliente
	 * @param id      identificador del cliente
	 * @return devulve un ResponseEntity que almacena mensajes y objetos en nuestro
	 *         caso cualquiere cosa ResponseEntity<?>
	 */
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Cliente cliente = clienteService.findbyId(id);
		Map<String, Object> response = new HashMap<>();
		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.putIfAbsent("mensaje", "Error al subir la imagen del cliente ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			uploadFileService.eliminar(nombreFotoAnterior);
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			response.put("cliente", cliente);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * método para buscar y mostrar la foto
	 * 
	 * @param nombreFoto es el nombre de la foto
	 * HttpHeaders es la cabecera podemos descargar la imagen
	 * @return devulve la foto
	 */
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.addIfAbsent(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
}
