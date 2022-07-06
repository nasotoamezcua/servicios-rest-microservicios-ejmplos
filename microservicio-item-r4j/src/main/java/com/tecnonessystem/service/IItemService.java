package com.tecnonessystem.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.tecnonessystem.model.Item;

public interface IItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);
	
	Item findById2(Long id, Integer cantidad);
	
	CompletableFuture<Item> findById3(Long id, Integer cantidad);

}
