package com.tecnonessystem.feing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tecnonessystem.feing.hystrix.ProductoFeignClienteHystrix;
import com.tecnonessystem.commons.entity.Producto;

@FeignClient(name = "microservicio-productos" , fallback = ProductoFeignClienteHystrix.class )
public interface IProductoFeignCliente {
	
	@GetMapping("/listar")
	List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	Producto detalle(@PathVariable("id") Long id);
	
	@PostMapping("/crear")
	Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	Producto editar(@RequestBody Producto producto, @PathVariable("id") Long id);
	
	@DeleteMapping("/eliminar/{id}")	
	void eliminar(@PathVariable("id") Long id);

}
