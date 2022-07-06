package com.tecnonessystem.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnonessystem.model.Item;
import com.tecnonessystem.model.Producto;
import com.tecnonessystem.service.IItemService;

@Service("itemServiceRestTemplateImpl")
public class ItemServiceRestTemplateImpl implements IItemService{
	
	@Autowired
	private RestTemplate clienteRestTemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos =  Arrays.asList(clienteRestTemplate.getForObject("http://microservicio-productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		Map<String, Object> pathVariables = new HashMap<String, Object>();
		pathVariables.put("id", id);
		
		Producto producto = clienteRestTemplate.getForObject("http://microservicio-productos/ver/{id}", Producto.class, pathVariables);
		
		return new Item(producto, cantidad);
	}

	@Override
	public Item findById2(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Item> findById3(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
