package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.model.Category;

public interface CategoryService {
	Category addCategory(Category productCategory);
	Category updateCategory(Category productCategory);
	Category deleteCategory(Integer categoryId);
	Category getCategoryByName(String name);
	Category getCategoryById(Integer categoryId);
	List<Category> getAllCategory();
}
