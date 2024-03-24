package com.zionique.productserviceapplication.repositories;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    Optional<Category> findCategoryByName(String categoryName);

    Optional<Category> findCategoryById(Long id);

    Integer deleteCategoriesById(Long id);

}
