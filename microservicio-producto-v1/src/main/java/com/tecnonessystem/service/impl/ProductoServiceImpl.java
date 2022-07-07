package com.tecnonessystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnonessystem.entity.Producto;
import com.tecnonessystem.repository.IProductoRepository;
import com.tecnonessystem.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoRepository repositoy;

	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) repositoy.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return repositoy.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Producto save(Producto producto) {
		return repositoy.save(producto);
	}


	@Override
	@Transactional
	public void deleteById(Long id) {
		repositoy.deleteById(id);
	}

}
