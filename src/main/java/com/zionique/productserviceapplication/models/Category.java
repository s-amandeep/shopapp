package com.zionique.productserviceapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Cascade({CascadeType.ALL})
    private List<Product> productList;
}
