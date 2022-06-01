package com.tecnonessytem.service;

import java.util.List;

import com.tecnonessytem.model.Item;

public interface IItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);

}
