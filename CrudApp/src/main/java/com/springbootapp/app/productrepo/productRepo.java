package com.springbootapp.app.productrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapp.app.product.Product;

public interface productRepo extends JpaRepository<Product, Long> {

}
