package com.BeeTech.Cartify.Dto;

import com.BeeTech.Cartify.Model.Category;
import java.math.BigDecimal;
import java.util.List;


public record ProductDto(
        Long id,
        String name,
        String brand,
        BigDecimal price,
        int inventory,
        String description,
        Category category,
        List<ImageDto> images
) {}
