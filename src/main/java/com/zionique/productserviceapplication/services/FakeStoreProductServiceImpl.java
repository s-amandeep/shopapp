package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.clients.fakeStoreApi.FakeStoreClient;
import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreClient fakeStoreClient;

    @Override
    public Optional<Product> getSingleProduct(Long id) {

        return fakeStoreClient.getSingleProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {

        return fakeStoreClient.getAllProducts();
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {

        return fakeStoreClient.deleteProduct(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {

        return fakeStoreClient.updateProduct(id, getProductDtoFromProduct(product));
    }

    @Override
    public Product addProduct(Product product) {

        return fakeStoreClient.addProduct(getProductDtoFromProduct(product));
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        return null;
    }

    private ProductDto getProductDtoFromProduct(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setCategory(product.getCategory().getName());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

}
