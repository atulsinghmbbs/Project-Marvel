package com.haarmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.HaarmkException;
import com.haarmk.model.Category;
import com.haarmk.repository.CategoryRepo;
import com.haarmk.service.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired private CategoryRepo categoryRepo;
	
	@Override
	public Category addCategory(Category category) {
		if(category == null) {
			throw new IllegalArgumentException("category should not be null.");
		}
		return categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		categoryRepo.findById(category.getId()).orElseThrow(() -> new HaarmkException("No category found by id: "+category.getId()));
		return categoryRepo.save(category);
	}

	@Override
	public Category deleteCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new HaarmkException("No category found by id: "+categoryId));
		categoryRepo.delete(category);
		return category;
	}

	@Override
	public Category getCategoryByName(String name) {
		
		return categoryRepo.findByName(name).orElseThrow(() -> new HaarmkException("No category found by id: "+name));
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		if(categories.isEmpty()) {
			throw new HaarmkException("No record found for category");
		}
		return categories;
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryRepo.findById(categoryId).orElseThrow(() -> new HaarmkException("No category found by id: "+categoryId));
	}

}
