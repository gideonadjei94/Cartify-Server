package com.BeeTech.Cartify.Dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;

    public ImageDto(){}

    public ImageDto(long imageId, String imageName, String downloadUrl){
        this.imageId = imageId;
        this.imageName = imageName;
        this.downloadUrl = downloadUrl;
    }
}
