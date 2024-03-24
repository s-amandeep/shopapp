package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Primary
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Override
    public Optional<Product> getSingleProduct(Long id) {
        Optional<Product> product = productRepository.getProductById(id);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> deleteProduct(Long id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return null;
//        return Optional.of(productRepository.updateDistinctByIdEquals(id, product));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        return productRepository.getProductsInCategory(categoryName);
    }

}
