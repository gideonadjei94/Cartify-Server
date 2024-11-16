package com.BeeTech.Cartify.Mappers;

import com.BeeTech.Cartify.Dto.ImageDto;
import com.BeeTech.Cartify.Dto.ProductDto;
import com.BeeTech.Cartify.Model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductMapper implements Function<Product, ProductDto> {
    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getInventory(),
                product.getDescription(),
                product.getCategory(),
                product.getImages().stream().map(image -> new ImageDto(
                        image.getId(),
                        image.getFileName(),
                        image.getDownloadUrl()
                )).collect(Collectors.toList())
        );
    }
}
