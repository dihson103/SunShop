package com.dinhson.sunshop.appProduct.sizes;

import com.dinhson.sunshop.exception.SizeAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    private boolean isSizeExist(Size size){
        return sizeRepository.findSizeBySize(size.getSize()).isPresent();
    }

    public void addNewSize(Size size){
        if(isSizeExist(size)){
            throw new SizeAlreadyExistException("Size " + size.getSize() + " is already exist!!!");
        }
        sizeRepository.save(size);
    }

    @Cacheable("sizes")
    public Iterable<Size> findAllSize(){
        return sizeRepository.findAll();
    }
}
