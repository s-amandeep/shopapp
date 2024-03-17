package com.zionique.productserviceapplication.controllers;

import com.zionique.productserviceapplication.clients.fakeStoreApi.FakeStoreProductDto;
import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.exceptions.NotFoundException;
import com.zionique.productserviceapplication.models.Product;
import com.zionique.productserviceapplication.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
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
        Product newProduct = productService.addProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }

//    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Optional<Product> updatedProduct = productService.updateProduct(id, productDto);
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

}
