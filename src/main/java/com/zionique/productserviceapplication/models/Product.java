package com.zionique.productserviceapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    /*
    If we make the fetch type as Lazy then we would also need to uncomment the Json ignore properties or
    Uncomment @Configuration and @Bean in JsonConfiguration file. It will result in Category field set to null
     */
    @Cascade({CascadeType.ALL})
    private Category category;
    private String imageUrl;
}
