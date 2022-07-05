package com.tecnonessystem.feing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tecnonessystem.feing.hystrix.ProductoFeignClienteHystrix;
import com.tecnonessystem.model.Producto;

@FeignClient(name = "servicio-productos", fallback = ProductoFeignClienteHystrix.class)
public interface IProductoFeignCliente {
	
	@GetMapping("/listar")
	List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable("id") Long id);

}
