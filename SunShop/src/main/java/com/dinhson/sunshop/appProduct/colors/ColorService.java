package com.dinhson.sunshop.appProduct.colors;

import com.dinhson.sunshop.appAdmin.colorManagement.ColorDTO;
import com.dinhson.sunshop.appAdmin.colorManagement.ColorDTOMapper;
import com.dinhson.sunshop.exception.ColorAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;
    private final ColorDTOMapper colorDTOMapper;

    public void addNewColor(Color color) {
        if (isColorExist(color.getName())) {
            throw new ColorAlreadyExistException("Color " + color.getName() + " is already exist!!!");
        }
        colorRepository.save(color);
    }

    private boolean isColorExist(String colorName) {
        return colorRepository.findColorByName(colorName).isPresent();
    }

    public List<Color> findAllColor() {
        return colorRepository.getAllColor();
    }

    public Color findColorById(Integer colorId){
        return colorRepository.findById(colorId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find color!!!"));
    }

    private List<ColorDTO> getAllColorDTO(){
        return findAllColor().stream()
                .map(colorDTOMapper)
                .collect(Collectors.toList());
    }

    public void createColor(String name){
        Color color = Color.builder().name(name).build();
        addNewColor(color);
    }

    private List<ColorDTO> searchByName(String name){
        return colorRepository.searchByName(name).stream()
                .map(colorDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ColorDTO> searchColor(String name){
        if (name == null){
            return getAllColorDTO();
        }
        return searchByName(name);
    }

    public String updateColor(Integer colorId, String colorName) {
        if(isColorExist(colorName)){
            return "Color with name: " + colorName + " is already exist!!!";
        }
        Color color = findColorById(colorId);
        color.setName(colorName);
        colorRepository.save(color);
        return "Update color success!!";
    }

    public void deleteColor(Integer colorId){
        Color color = findColorById(colorId);
        colorRepository.delete(color);
    }
}
