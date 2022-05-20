package net.develop.product.repository;

import org.springframework.data.repository.CrudRepository;

import net.develop.product.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
