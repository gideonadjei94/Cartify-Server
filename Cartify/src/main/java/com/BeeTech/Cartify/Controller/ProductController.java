package com.BeeTech.Cartify.Controller;

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

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductServiceInt productServiceInt;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productServiceInt.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("success",products));
    }


    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productServiceInt.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("fetch success", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product theProduct = productServiceInt.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product successfully added", theProduct));
        } catch (Exception e) {
       return ResponseEntity.status(INTERNAL_SERVER_ERROR)
               .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/product/{productId}")
    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateproductRequest request, @PathVariable Long productId){
        try {
            Product theProduct = productServiceInt.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse("Product successfully updated", theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productServiceInt.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product successfully deleted", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
