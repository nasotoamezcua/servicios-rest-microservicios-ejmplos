package com.tecnonessystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tecnonessystem.model.Item;
import com.tecnonessystem.service.IItemService;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("itemServiceFeingCliente")
	private IItemService service;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return service.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return service.findById(id, cantidad);		
	}

}
