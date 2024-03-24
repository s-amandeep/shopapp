package com.zionique.productserviceapplication.repositories;

public interface Queries {
    String GET_PRODUCTS_IN_CATEGORY_QUERY = "SELECT p from product p where p.category.name = :categoryName";
}
