package com.BeeTech.Cartify.Service.Image;

import com.BeeTech.Cartify.Dto.ImageDto;
import com.BeeTech.Cartify.Model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageServiceInt {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
