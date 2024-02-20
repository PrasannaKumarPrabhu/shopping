package com.shopping.productservice.repository;

import com.shopping.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<Product, String> {
}
