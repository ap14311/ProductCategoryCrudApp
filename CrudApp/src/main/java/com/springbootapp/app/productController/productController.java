package com.springbootapp.app.productController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapp.app.product.Product;
import com.springbootapp.app.productrepo.productRepo;

@RestController
@RequestMapping("/api/products")

public class productController {
	
	 @Autowired
	    private productRepo productRepository;

	    @GetMapping
	    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
	        // Implement pagination logic if needed.
	        return productRepository.findAll();
	    }

	    @PostMapping
	    public Product createProduct(@RequestBody Product product) {
	        return productRepository.save(product);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        return productRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	        return productRepository.findById(id)
	                .map(product -> {
	                    product.setName(productDetails.getName());
	                    return ResponseEntity.ok(productRepository.save(product));
	                }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        return productRepository.findById(id)
	                .map(product -> {
	                    productRepository.delete(product);
	                    return ResponseEntity.noContent().build();
	                }.orElse(ResponseEntity.notFound().build());

	    }
}
