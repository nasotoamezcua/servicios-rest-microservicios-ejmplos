package com.tecnonessystem.service;

import java.util.List;

import com.tecnonessystem.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Producto findById(Long id);

}
