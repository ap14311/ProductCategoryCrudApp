package com.springbootapp.app.catagoryrepo;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapp.app.catagoryentity.CatogoryClass;

public interface CategoryRepo extends JpaRepository<CatogoryClass, Long>  {
	

}
