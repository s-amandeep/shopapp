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
    public Optional<Product> updateProduct(Long id, ProductDto productDto) {

        return fakeStoreClient.updateProduct(id, productDto);
    }

    @Override
    public Product addProduct(ProductDto productDto) {

        return fakeStoreClient.addProduct(productDto);
    }

}
