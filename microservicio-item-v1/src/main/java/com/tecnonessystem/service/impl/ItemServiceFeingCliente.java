package com.tecnonessystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnonessystem.feing.IProductoFeignCliente;
import com.tecnonessystem.model.Item;
import com.tecnonessystem.commons.entity.Producto;
import com.tecnonessystem.service.IItemService;

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

	@Override
	public Producto save(Producto producto) {
		return feingCliente.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return feingCliente.editar(producto, id);
	}

	@Override
	public void delete(Long id) {
		feingCliente.eliminar(id);
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
