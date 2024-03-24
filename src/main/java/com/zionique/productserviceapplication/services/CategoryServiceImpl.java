package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
//        return categoryRepository.findBy
        return null;
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    @Transactional
    public Integer deleteCategoryById(Long id) {
        return categoryRepository.deleteCategoriesById(id);
    }

    @Override
    public Category addNewCategory(Category category) {
        return categoryRepository.save(category);
    }
}
