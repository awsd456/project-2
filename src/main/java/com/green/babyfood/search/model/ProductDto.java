package com.green.babyfood.search.model;

import lombok.Data;

@Data
public class ProductDto {
    private int productid;
    private String name;
    private String img;
    private int price;
}