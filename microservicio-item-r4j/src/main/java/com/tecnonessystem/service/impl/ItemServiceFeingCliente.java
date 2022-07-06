package com.tecnonessystem.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.tecnonessystem.feing.IProductoFeignCliente;
import com.tecnonessystem.model.Item;
import com.tecnonessystem.model.Producto;
import com.tecnonessystem.service.IItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service("itemServiceFeingCliente")
public class ItemServiceFeingCliente implements IItemService{
	
	private final Logger logger = LoggerFactory.getLogger(ItemServiceFeingCliente.class);
	
	@Autowired
	private CircuitBreakerFactory cbfactory;
	
	@Autowired
	private IProductoFeignCliente feingCliente;

	@Override
	public List<Item> findAll() {
		return feingCliente.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}
	
	@Override
	public Item findById(Long id, Integer cantidad) {
		return cbfactory.create("items").run(() -> new Item(feingCliente.detalle(id), cantidad) , e -> metodoAlternativo(id, cantidad, e));
	}
	
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
	@Override
	public Item findById2(Long id, Integer cantidad) {
		return  new Item(feingCliente.detalle(id), cantidad);
	}
	
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name = "items")
	@Override
	public CompletableFuture<Item> findById3(Long id, Integer cantidad) {
		return  CompletableFuture.supplyAsync(() -> new Item(feingCliente.detalle(id), cantidad));
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
		
		logger.info(e.getMessage());
		
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Producto Alternativo RJ4");
		producto.setPrecio(500.00);
		
		item.setProducto(producto);
		
		return item;
	}
	
	public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
		
		logger.info(e.getMessage());
		
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Producto Alternativo Time Out RJ4");
		producto.setPrecio(500.00);
		
		item.setProducto(producto);
		
		return CompletableFuture.supplyAsync(() -> item);
	}	
}
