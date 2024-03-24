package com.zionique.productserviceapplication;

import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.repositories.CategoryRepository;
import com.zionique.productserviceapplication.repositories.ProductRepository;
import com.zionique.productserviceapplication.repositories.Queries;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void addCategory(){

    }

//    @Test
    void addProducts(){
        Category category = new Category();
        category.setName("Toys");
        Category category1 = categoryRepository.save(category);

        Product product = Product.builder()
                .title("RnD")
                .price(589)
                .imageUrl("image")
                .description("abc")
                .category(category1)
                .build();

        Product product1 = productRepository.save(product);
    }

//    @Test
    @Transactional
    void testCategoryRepository(){
//        List<Category> categories = categoryRepository.findCategoryById(152L);
//        List<Category> categories = categoryRepository.f("Books");

        List<Product> products = productRepository.getProductsInCategory("Sports");

        for (Product product : products){
            System.out.println(product.getTitle());
        }


    }

}
