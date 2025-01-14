package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Dto.ProductDto;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.Product;
import com.BeeTech.Cartify.Request.AddProductRequest;
import com.BeeTech.Cartify.Request.UpdateproductRequest;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.Product.ProductServiceInt;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServiceInt productServiceInt;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<ProductDto> products = productServiceInt.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("success",products));
    }


    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            ProductDto product = productServiceInt.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("fetch success", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            ProductDto theProduct = productServiceInt.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product successfully added", theProduct));
        } catch (Exception e) {
       return ResponseEntity.status(CONFLICT)
               .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/product/update/{productId}")
    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateproductRequest request, @PathVariable Long productId){
        try {
            Product theProduct = productServiceInt.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse("Product successfully updated", theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productServiceInt.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product successfully deleted", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/by-brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String productBrand, @RequestParam String productName){
        try {
            List<ProductDto> products = productServiceInt.getProductsByBrandAndName(productBrand, productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }


    @GetMapping("/by-category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<ProductDto> products = productServiceInt.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Serverside error", e.getMessage()));
        }
    }


    @GetMapping("/product")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name){
        try {
            List<ProductDto> products = productServiceInt.getProductsByName(name);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server side error", e.getMessage()));
        }
    }


    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
        try {
            List<ProductDto> products = productServiceInt.getProductsByBrand(brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server side error", e.getMessage()));
        }
    }


    @GetMapping("/by-category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category){
        try {
            List<ProductDto> products = productServiceInt.getProductsByCategory(category);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Server side error", e.getMessage()));
        }
    }


    @GetMapping("/product/count")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name){
        try {
            var productCount = productServiceInt.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An error occured", e.getMessage()));
        }
    }
}
