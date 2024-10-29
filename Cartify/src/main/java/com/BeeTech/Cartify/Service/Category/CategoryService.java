package com.BeeTech.Cartify.Service.Category;

import com.BeeTech.Cartify.Exceptions.ResourceNotFound;
import com.BeeTech.Cartify.Model.Category;
import com.BeeTech.Cartify.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInt{

    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
