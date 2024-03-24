package com.zionique.productserviceapplication.repositories;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.services.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query("select p from Product p where p.id = :id")
    Optional<Product> getProductById(Long id);

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> deleteProductById(Long id);

    @Query("SELECT p from Product p where p.category.name = :categoryName")
    List<Product> getProductsInCategory(String categoryName);

//    Optional<Product> updateProductById(Long id, Product product);
//    Product updateDistinctByIdEquals(Long id, Product product);

}
