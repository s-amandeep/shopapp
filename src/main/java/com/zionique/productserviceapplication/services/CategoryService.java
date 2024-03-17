package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<String> getAllCategories();

    List<Product> getProductsInCategory(String categoryName);
}
