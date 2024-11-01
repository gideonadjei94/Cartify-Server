package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Exceptions.AlreadyExistsException;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.Category;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.Category.CategoryServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceInt categoryServiceInt;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> categories = categoryServiceInt.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Fetch success", categories));
        } catch (Exception e) {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse("Categories fetch failed",INTERNAL_SERVER_ERROR ));
        }

    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category theCategory = categoryServiceInt.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category added successfully", theCategory));
        } catch (AlreadyExistsException e) {
            return  ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category theCategory = categoryServiceInt.getCategoryById(id);
            return  ResponseEntity.ok(new ApiResponse("Category found", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/category/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try {
            Category theCategory = categoryServiceInt.getCategoryByName(name);
            return  ResponseEntity.ok(new ApiResponse("Category found", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        try {
            categoryServiceInt.deleteCategoryById(id);
            return  ResponseEntity.ok(new ApiResponse("Category found", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id , @RequestBody Category category){
        try {
            Category updatedCategory = categoryServiceInt.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category successfully updated", updatedCategory));
        } catch (ResourceNotFoundException e) {
           return  ResponseEntity.status(NOT_FOUND)
                   .body(new ApiResponse(e.getMessage(),null));
        }
    }
}
