package com.mystore.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name should not be null,empty or blank")
    private String name;

    @NotBlank(message = "category should not be null,empty or blank")
    private String category;

    @NotNull(message = "price should not be null")
    @Positive(message = "price should be positive")
    @Min(value = 100,message = "Please don't add any product with price lesser than 100")
    @Max(value= 50000,message = "This platform doesn't allow high priced products. Prices must be <= 50000")
    private Double price;

    @NotNull(message = "stockQuantity should not be null")
    @Positive(message = "stockQuantity should be positive")
    @Min(value = 10,message = "stockQuantity must be greater than 10")
    @Max(value= 500,message = "stockQuantity must be less than 500")
    private Integer stockQuantity;

    public Product() {
    }

    public Product(Integer id, String name, String category, Double price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
