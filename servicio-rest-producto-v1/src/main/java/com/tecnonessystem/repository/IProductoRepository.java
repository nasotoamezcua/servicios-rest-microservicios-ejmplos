package com.tecnonessystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecnonessystem.entity.Producto;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long> {

}
