package com.BeeTech.Cartify.Service.Product;

import com.BeeTech.Cartify.Dto.ProductDto;
import com.BeeTech.Cartify.Model.Product;
import com.BeeTech.Cartify.Request.AddProductRequest;
import com.BeeTech.Cartify.Request.UpdateproductRequest;

import java.util.List;

public interface ProductServiceInt {
    ProductDto addProduct(AddProductRequest product);
    ProductDto getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateproductRequest product, Long productId);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByCategory(String category);
    List<ProductDto> getProductsByBrand(String brand);
    List<ProductDto> getProductsByCategoryAndBrand(String category, String brand);
    List<ProductDto> getProductsByName(String name);
    List<ProductDto> getProductsByBrandAndName(String band, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
