package com.BeeTech.Cartify.Service.Product;

import com.BeeTech.Cartify.Exceptions.ProductNotFoundException;
import com.BeeTech.Cartify.Model.Product;
import com.BeeTech.Cartify.Repository.ProductRepository;

import java.util.List;

public class ProductService implements ProductServiceInt {

    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> new ProductNotFoundException("Product not found"));

    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return null;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByBrandAndName(String band, String name) {
        return null;
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return null;
    }
}
