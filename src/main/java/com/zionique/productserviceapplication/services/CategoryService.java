package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getProductsInCategory(String categoryName);

    Optional<Category> getCategoryByName(String categoryName);

    Optional<Category> getCategoryById(Long id);

    Integer deleteCategoryById(Long id);

    Category addNewCategory(Category category);
}
