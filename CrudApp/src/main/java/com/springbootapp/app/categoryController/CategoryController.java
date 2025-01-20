


	package com.springbootapp.app.categoryController;

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

	import com.springbootapp.app.catagoryentity.CatogoryClass; // Ensure this is the correct package
	import com.springbootapp.app.catagoryrepo.CategoryRepo;

	@RestController
	@RequestMapping("/api/categories")
	public class CategoryController {

	    @Autowired
	    private CategoryRepo categoryRepository;

	    @GetMapping
	    public List<CatogoryClass> getAllCategories(@RequestParam(defaultValue = "0") int page) {
	        // Implement pagination logic if needed.
	        return categoryRepository.findAll();
	    }

	    @PostMapping
	    public ResponseEntity<CatogoryClass> createCategory(@RequestBody CatogoryClass category) {
	        // Optionally validate the category object here
	        if (category.getName() == null || category.getName().isEmpty()) {
	            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if name is invalid
	        }
	        CatogoryClass savedCategory = categoryRepository.save(category);
	        return ResponseEntity.status(201).body(savedCategory); // Return 201 Created with the saved category
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CatogoryClass> getCategoryById(@PathVariable Long id) {
	        return categoryRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CatogoryClass> updateCategory(@PathVariable Long id, @RequestBody CatogoryClass categoryDetails) {
	        return categoryRepository.findById(id)
	                .map(category -> {
	                    category.setName(categoryDetails.getName());
	                    return ResponseEntity.ok(categoryRepository.save(category));
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<catogory> deleteCategory(@PathVariable Long id) {
	        return categoryRepository.findById(id)
	                .map(category -> {
	                    categoryRepository.delete(category);
	                    return ResponseEntity.noContent().build(); // Return 204 No Content
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }
	}



