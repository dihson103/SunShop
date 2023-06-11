package com.dinhson.sunshop.appProduct.images;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void addNewImages(List<Image> images){
        for (Image image: images) {
            imageRepository.save(image);
        }
    }
}
