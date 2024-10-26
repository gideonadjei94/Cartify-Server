package com.BeeTech.Cartify.Service.Product;

import com.BeeTech.Cartify.Model.Product;
import com.BeeTech.Cartify.Request.AddProductRequest;
import com.BeeTech.Cartify.Request.UpdateproductRequest;

import java.util.List;

public interface ProductServiceInt {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateproductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String band, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
