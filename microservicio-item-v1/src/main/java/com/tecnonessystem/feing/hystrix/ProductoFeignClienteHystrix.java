package com.tecnonessystem.feing.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tecnonessystem.feing.IProductoFeignCliente;
import com.tecnonessystem.model.Producto;

@Component
public class ProductoFeignClienteHystrix implements IProductoFeignCliente {
	
	private Logger logger = LoggerFactory.getLogger(ProductoFeignClienteHystrix.class);

	@Override
	public List<Producto> listar() {
		List<Producto> productos = new ArrayList<>();
		
		Producto p = new Producto();
		p.setId(0L);
		p.setNombre("Lista de Productos Alternativo");
		p.setPrecio(500.00);
		
		productos.add(p);
		
		return productos;
	}

	@Override
	public Producto detalle(Long id) {
		Producto producto = new Producto();
		producto.setId(id);
		producto.setNombre("Producto Alternativo");
		producto.setPrecio(500.00);
		
		return producto;
	}

	@Override
	public Producto crear(Producto producto) {
		
		logger.info("Producto no Creado");
		
		producto.setNombre("Producto no Creado");
		producto.setPrecio(0.0);
		
		return producto;
	}

	@Override
	public Producto editar(Producto producto, Long id) {
		
		logger.info("Producto no Actualizdo");
		
		producto.setNombre("Producto no Actualizdo");
		producto.setPrecio(0.0);
		
		return producto;
	}

	@Override
	public void eliminar(Long id) {
		logger.info("Producto no Eliminado");
		
	}

}
