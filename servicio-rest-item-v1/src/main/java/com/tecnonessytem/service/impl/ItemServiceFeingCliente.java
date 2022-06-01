package com.tecnonessytem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnonessytem.feing.IProductoFeignCliente;
import com.tecnonessytem.model.Item;
import com.tecnonessytem.service.IItemService;

@Service("itemServiceFeingCliente")
public class ItemServiceFeingCliente implements IItemService{
	
	@Autowired
	private IProductoFeignCliente feingCliente;

	@Override
	public List<Item> findAll() {
		return feingCliente.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	//@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(feingCliente.detalle(id), cantidad);
	}
	
	/*
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Producto Alternativo");
		producto.setPrecio(500.00);
		
		item.setProducto(producto);
		
		return item;
	}
	*/

}
