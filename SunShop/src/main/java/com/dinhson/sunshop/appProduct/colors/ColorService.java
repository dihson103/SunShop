package com.dinhson.sunshop.appProduct.colors;

import com.dinhson.sunshop.exception.ColorAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    public void addNewColor(Color color){
        if(isColorExist(color)){
            throw new ColorAlreadyExistException("Color " + color.getName() + " is already exist!!!");
        }
        colorRepository.save(color);
    }

    private boolean isColorExist(Color color) {
        return colorRepository.findColorByName(color.getName()).isPresent();
    }
}
