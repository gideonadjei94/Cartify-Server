package com.BeeTech.Cartify.Request;

import com.BeeTech.Cartify.Model.Category;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class UpdateproductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;

}
