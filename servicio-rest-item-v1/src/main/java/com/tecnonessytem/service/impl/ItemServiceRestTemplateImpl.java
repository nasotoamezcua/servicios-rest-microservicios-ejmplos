package com.tecnonessytem.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnonessytem.model.Item;
import com.tecnonessytem.model.Producto;
import com.tecnonessytem.service.IItemService;

@Service("itemServiceRestTemplateImpl")
public class ItemServiceRestTemplateImpl implements IItemService{
	
	@Autowired
	private RestTemplate clienteRestTemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos =  Arrays.asList(clienteRestTemplate.getForObject("http://servicio-productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		Map<String, Object> pathVariables = new HashMap<String, Object>();
		pathVariables.put("id", id);
		
		Producto producto = clienteRestTemplate.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
		
		return new Item(producto, cantidad);
	}

}
