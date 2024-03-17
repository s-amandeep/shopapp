package com.zionique.productserviceapplication.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
