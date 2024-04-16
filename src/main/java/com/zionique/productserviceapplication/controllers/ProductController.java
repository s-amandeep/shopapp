package com.zionique.productserviceapplication.controllers;

import com.zionique.productserviceapplication.clients.authenticationClient.AuthenticationClient;
import com.zionique.productserviceapplication.clients.authenticationClient.dtos.Role;
import com.zionique.productserviceapplication.clients.authenticationClient.dtos.SessionStatus;
import com.zionique.productserviceapplication.clients.authenticationClient.dtos.ValidateTokenResponseDto;
import com.zionique.productserviceapplication.clients.fakeStoreApi.FakeStoreProductDto;
import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.exceptions.NotFoundException;
import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private CategoryController categoryController;
    private AuthenticationClient authenticationClient;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws NotFoundException {
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
        headerMap.add("auth_token", "b675fgd5363");

        Optional<Product> product = productService.getSingleProduct(id);
        if (product.isEmpty()){
            throw new NotFoundException("Product with id: " + id + " not found");
        }
        ResponseEntity responseEntity = new ResponseEntity(product.get(), headerMap, HttpStatus.OK);
        return responseEntity;
    }


    // Implementing Authentication, task: Make only admins access all the products

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                        @Nullable @RequestHeader("USER_ID") Long userId){

        // Check if token exists
        if (token == null || userId == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ValidateTokenResponseDto responseDto = authenticationClient.validate(token, userId);

        // Check if token is valid
        if (responseDto.getSessionStatus().equals(SessionStatus.INVALID)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Check if user has permissions
        boolean isUserAdmin = false;
        for (Role role: responseDto.getUserDto().getRoles()){
            if (role.getName().equals("ADMIN")){
                isUserAdmin = true;
            }
        }

        if (!isUserAdmin){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Optional<Product> product = productService.deleteProduct(id);
        if (product.isEmpty()){
            try {
                throw new NotFoundException("Product with id: " + id + " not found");
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        ResponseEntity responseEntity = new ResponseEntity(product.get(), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){

        Product newProduct = productService.addProduct(getProductFromProductDto(productDto));
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }

//    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Optional<Product> updatedProduct = productService.updateProduct(id, getProductFromProductDto(productDto));
        if (updatedProduct.isEmpty()){
            try {
                throw new NotFoundException("Product with id: " + id + " not found");
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        ResponseEntity<Product> response = new ResponseEntity<>(updatedProduct.get(), HttpStatus.OK);
        return response;
    }

    private Product getProductFromProductDto(ProductDto productDto){
        Category category = categoryController.getCategoryByName(productDto.getCategory());

        Product product = Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImage())
                .category(category)
                .build();

        return product;
    }

//    @GetMapping("category/{categoryName}")
//    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName){
//        return productService.getProductsInCategory(categoryName);
//    }

}
