package com.api.rating_product.domain.product;

import com.api.rating_product.DTOS.product.ProductDTO;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity(name = "products")
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    public Product (){

    }

    public Product (ProductDTO data){

        this.name = data.name();
        this.description = data.description();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object){

        return  EqualsBuilder.reflectionEquals(object , this);

    }
}
