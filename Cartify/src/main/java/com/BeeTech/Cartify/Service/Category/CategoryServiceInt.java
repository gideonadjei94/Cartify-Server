package com.BeeTech.Cartify.Service.Category;

import com.BeeTech.Cartify.Model.Category;

import java.util.List;

public interface CategoryServiceInt{
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
    List<Category> getAllCategories();
}
