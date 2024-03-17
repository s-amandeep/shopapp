package com.zionique.productserviceapplication.clients.fakeStoreApi;

import com.zionique.productserviceapplication.dtos.ProductDto;
import com.zionique.productserviceapplication.models.Category;
import com.zionique.productserviceapplication.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private static final String BASEURI = "https://fakestoreapi.com/products";

    public Optional<Product> getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                BASEURI+"/{id}", FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if (fakeStoreProductDto == null){
            return Optional.empty();
        }

        return Optional.of(getProductFromFakeStoreProductDto(fakeStoreProductDto));
    }

    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                BASEURI, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.asList(response.getBody());

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }

        return products;
    }

    public Optional<Product> deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = requestForEntity(HttpMethod.DELETE,
                BASEURI+"/{id}", null, FakeStoreProductDto.class, id);
        if (fakeStoreProductDto == null){
            return Optional.empty();
        }

        return Optional.of(getProductFromFakeStoreProductDto(fakeStoreProductDto.getBody()));
    }

    public Optional<Product> updateProduct(Long id, ProductDto productDto) {

        /*
        Code for PATCH - Will Update the fields passed as request, rest all will remain same at same id
        PUT - Will Replace the existing product at same id--- Change the method to PUT
         */

        ResponseEntity<FakeStoreProductDto> response = requestForEntity(HttpMethod.PATCH, BASEURI+"/{id}", productDto,
                FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null){
            return Optional.empty();
        }

        return Optional.of(getProductFromFakeStoreProductDto(fakeStoreProductDto));
    }

    public Product addProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(BASEURI, productDto, FakeStoreProductDto.class);
        return getProductFromFakeStoreProductDto(response.getBody());
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> categories = restTemplate.getForEntity(BASEURI+"/categories", String[].class);

         return Arrays.asList(categories.getBody());
    }

    public List<Product> getProductsInCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(BASEURI+"/category/" + categoryName,
                FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: response.getBody()){
            products.add(getProductFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return products;
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());

        Product product = Product.builder()
                .title(fakeStoreProductDto.getTitle())
                .price(fakeStoreProductDto.getPrice())
                .description(fakeStoreProductDto.getDescription())
                .imageUrl(fakeStoreProductDto.getImage())
                .category(category)
                .build();

        product.setId(fakeStoreProductDto.getId());

        return product;
    }
}
