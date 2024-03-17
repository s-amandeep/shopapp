package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getSingleProduct(Long id);
    List<Product> getAllProducts();
    Optional<Product> deleteProduct(Long id);
    Optional<Product> updateProduct(Long id, ProductDto productDto);
    Product addProduct(ProductDto productDto);
}
