package com.tecnonessytem.feing.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tecnonessytem.feing.IProductoFeignCliente;
import com.tecnonessytem.model.Producto;

@Component
public class ProductoFeignClienteHystrix implements IProductoFeignCliente {

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

}
