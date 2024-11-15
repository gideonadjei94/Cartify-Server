package com.BeeTech.Cartify.Controller;

import com.BeeTech.Cartify.Dto.ImageDto;
import com.BeeTech.Cartify.Exceptions.FileprocessingException;
import com.BeeTech.Cartify.Exceptions.ResourceNotFoundException;
import com.BeeTech.Cartify.Model.Image;
import com.BeeTech.Cartify.Response.ApiResponse;
import com.BeeTech.Cartify.Service.Image.ImageServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageServiceInt imageServiceInt;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
        List<ImageDto> imageDtos = null;
        try {
            imageDtos = imageServiceInt.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("Image saved successfully", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Image Upload failed",e.getMessage()));
        }

    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageServiceInt.getImageById(imageId);
        if(image == null){
            throw new ResourceNotFoundException("Image not found");
        }

        try {
            if(image.getImage() == null){
                throw new IllegalAccessException("Image data is null");
            }

            byte[] imageData = image.getImage(); // Directly access the byte array
            ByteArrayResource resource = new ByteArrayResource(imageData);

            String fileType = image.getFileType() != null ? image.getFileType() : "application/octet-stream";
            String fileName = image.getFileName() != null ? image.getFileName() : "default_filename";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (IllegalAccessException e) {
            throw new FileprocessingException("Error processing image Blob", e);
        }
//        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int)image.getImage().length()));
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
//                .body(resource);
    }


    @PutMapping("/image/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){
        try {
            Image image = imageServiceInt.getImageById(imageId);
            if(image != null){
                imageServiceInt.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Update success", null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed", INTERNAL_SERVER_ERROR));
    }


    @DeleteMapping("/image/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try {
            Image image = imageServiceInt.getImageById(imageId);
            if(image != null){
                imageServiceInt.deleteImageById( imageId);
                return ResponseEntity.ok(new ApiResponse("Image deleted successfully", null));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Image Deletion failed", INTERNAL_SERVER_ERROR));
    }


}
