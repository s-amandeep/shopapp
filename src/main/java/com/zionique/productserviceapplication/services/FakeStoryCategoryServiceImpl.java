package com.zionique.productserviceapplication.services;

import com.zionique.productserviceapplication.clients.fakeStoreApi.FakeStoreClient;
import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FakeStoryCategoryServiceImpl implements CategoryService{

    private FakeStoreClient fakeStoreClient;

    @Override
    public List<String> getAllCategories() {

        return fakeStoreClient.getAllCategories();
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {

        return fakeStoreClient.getProductsInCategory(categoryName);
    }
}
