package com.tecnonessystem.service;

import java.util.List;

import com.tecnonessystem.model.Item;

public interface IItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);

}
