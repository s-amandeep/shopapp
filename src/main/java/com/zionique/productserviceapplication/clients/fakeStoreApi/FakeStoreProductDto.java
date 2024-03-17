package com.zionique.productserviceapplication.clients.fakeStoreApi;

import com.zionique.productserviceapplication.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
    private RatingDto rating;
}
