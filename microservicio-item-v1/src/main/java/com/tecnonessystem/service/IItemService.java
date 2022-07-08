package com.tecnonessystem.service;

import java.util.List;

import com.tecnonessystem.model.Item;
import com.tecnonessystem.commons.entity.Producto;

public interface IItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);
	
	Producto save(Producto producto);
	
	Producto update(Producto producto, Long id);
	
	void delete(Long id);

}
