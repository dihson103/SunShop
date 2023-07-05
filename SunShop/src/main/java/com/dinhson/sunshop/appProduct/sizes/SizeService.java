package com.dinhson.sunshop.appProduct.sizes;

import com.dinhson.sunshop.exception.SizeAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    private boolean isSizeExist(String size) {
        return sizeRepository.findSizeBySize(size).isPresent();
    }

    public void addNewSize(Size size) {
        if (isSizeExist(size.getSize())) {
            throw new SizeAlreadyExistException("Size " + size.getSize() + " is already exist!!!");
        }
        sizeRepository.save(size);
    }

    public Iterable<Size> findAllSize() {
        return sizeRepository.findAll();
    }

    public Size findSizeById(Integer sizeId){
        return sizeRepository.findById(sizeId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find size!!!"));
    }

    public String createNewSize(String name, String height, String weight){
        if(isSizeExist(name)){
            return "Size " + name + " is already exist!!!";
        }

        Size size = new Size(name, height, weight);
        sizeRepository.save(size);
        return "Add new size success!!!";
    }

    public String updateSize(Integer id, String name, String height, String weight){
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find size!!!"));

        if(isSizeExist(name) && !size.getSize().equals(name)){
            return "Size " + name + " is already exist!!!";
        }

        size.setSize(name);
        size.setHeight(height);
        size.setWeight(weight);
        sizeRepository.save(size);
        return "Update size success!!!";
    }

    public void deleteSize(Integer id){
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can not find size!!!"));
        sizeRepository.delete(size);
    }
}
