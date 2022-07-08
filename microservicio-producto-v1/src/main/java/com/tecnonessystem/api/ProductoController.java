package com.tecnonessystem.api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnonessystem.commons.entity.Producto;
import com.tecnonessystem.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService service;
	
	@GetMapping("/listar")
	public List<Producto> litsar() {
		
		return service.findAll().stream().map(p -> {
			p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable("id") Long id) throws InterruptedException {
		
		/* Probar Hystrix
		boolean error = false;
		if(!error)	throw new RuntimeException("Error lanzado");
		*/
		
		/* Probar Resilence4J */
		if(id.equals(10L)) throw new IllegalStateException("Producto no encontrado!");
		
		if(id.equals(7L)) TimeUnit.SECONDS.sleep(5L);
		
		Producto p = service.findById(id);
		p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//p.setPort(port);
		
		
		/* PROBAR TIME OUT PARA RIBON , HYSTRIX y  ZUUL
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		return p;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return service.save(producto); 
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable("id") Long id) {
		
		Producto p = service.findById(id);		
		p.setNombre(producto.getNombre());
		p.setPrecio(producto.getPrecio());
		
		return service.save(p); 
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable("id") Long id) {
		service.deleteById(id);
	}

}