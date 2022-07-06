package com.tecnonessystem.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnonessystem.model.Item;
import com.tecnonessystem.service.IItemService;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("itemServiceFeingCliente")
	private IItemService service;
	
	@GetMapping("/listar")
	public List<Item> listar(
			@RequestParam(name = "nombre", required = false) String nombre, 
			@RequestHeader(name = "token-request", required = false) String token) {
		System.out.println(nombre);
		System.out.println(token);
		return service.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return service.findById(id, cantidad);		
	}
	
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return service.findById2(id, cantidad);		
	}
	
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return service.findById3(id, cantidad);		
	}

}
