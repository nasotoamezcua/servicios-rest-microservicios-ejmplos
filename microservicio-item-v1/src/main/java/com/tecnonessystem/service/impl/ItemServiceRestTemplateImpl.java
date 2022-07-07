package com.tecnonessystem.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response =  clienteRestTemplate.exchange("http://microservicio-productos/crear", HttpMethod.POST, body, Producto.class);
		return response.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		Map<String, Object> pathVariables = new HashMap<String, Object>();
		pathVariables.put("id", id);
		
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response =  clienteRestTemplate.exchange("http://microservicio-productos/editar/{id}", HttpMethod.PUT, body, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		
		Map<String, Object> pathVariables = new HashMap<String, Object>();
		pathVariables.put("id", id);
		
		clienteRestTemplate.delete("http://microservicio-productos/eliminar/{id}", pathVariables);
		
	}

}
