package com.zionique.productserviceapplication.controllers;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("categories")
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        return categoryService.getProductsInCategory(categoryName);
    }
}
