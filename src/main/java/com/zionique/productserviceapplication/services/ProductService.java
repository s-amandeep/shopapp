package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getSingleProduct(Long id);
    List<Product> getAllProducts();
    Optional<Product> deleteProduct(Long id);
    Optional<Product> updateProduct(Long id, Product product);
    Product addProduct(Product product);
    List<Product> getProductsInCategory(String categoryName);
}
