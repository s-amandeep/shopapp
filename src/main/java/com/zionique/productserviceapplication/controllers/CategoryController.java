package com.zionique.productserviceapplication.controllers;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.services.CategoryService;
import com.zionique.productserviceapplication.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}")
    public Category getCategoryByName(@PathVariable("categoryName") String categoryName){
        Optional<Category> category = categoryService.getCategoryByName(categoryName);

        if (category.isEmpty()){
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            Category savedCategory = categoryService.addNewCategory(newCategory);
            return savedCategory;
        }
//        List<Product> products = category.get().getProductList();

        return category.get();
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable("id") Long id){
        int value = categoryService.deleteCategoryById(id);
        if (value == 1 ){
            return "Category deleted Successfully";
        }

        return "No Such Category Exists";
    }

    @GetMapping("/products/{categoryName}")
    public List<ProductDto> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        Category category = getCategoryByName(categoryName);
        List<Product> products = category.getProductList();
        List<ProductDto> response = new ArrayList<>();

        for (Product product : products){
            ProductDto tempProductDto = getProductDtoFromProduct(product);
            tempProductDto.setCategory(categoryName);
            response.add(tempProductDto);
        }
        return response;
    }

    private ProductDto getProductDtoFromProduct(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        productDto.setId(product.getId());
        return productDto;
    }
}
