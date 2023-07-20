package com.dinhson.sunshop.appProduct.colors;

import com.dinhson.sunshop.appAdmin.colorManagement.ColorDTO;
import com.dinhson.sunshop.appAdmin.colorManagement.ColorDTOMapper;
import com.dinhson.sunshop.exception.ColorAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;
    private final ColorDTOMapper colorDTOMapper;
    private static Integer totalPages = 0;

    public void addNewColor(Color color) {
        if (isColorExist(color.getName())) {
            throw new ColorAlreadyExistException("Color " + color.getName() + " is already exist!!!");
        }
        colorRepository.save(color);
    }

    private boolean isColorExist(String colorName) {
        return colorRepository.findColorByName(colorName).isPresent();
    }

    public Page<Color> findAllColor(Pageable pageable) {
        return colorRepository.getAllColor(pageable);
    }

    public List<Color> findAllColor() {
        return colorRepository.getAllColor();
    }

    public Color findColorById(Integer colorId){
        return colorRepository.findById(colorId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find color!!!"));
    }

    private List<ColorDTO> getAllColorDTO(Pageable pageable){
        Page<Color> colorPage = findAllColor(pageable);
        totalPages = colorPage.getTotalPages();

        return colorPage.stream()
                .map(colorDTOMapper)
                .collect(Collectors.toList());
    }

    public void createColor(String name){
        Color color = Color.builder().name(name).build();
        addNewColor(color);
    }

    private List<ColorDTO> searchByName(String name, Pageable pageable){
        Page<Color> colorPage = colorRepository.searchByName(name, pageable);
        totalPages = colorPage.getTotalPages();

        return colorPage.stream()
                .map(colorDTOMapper)
                .collect(Collectors.toList());
    }

    public List<ColorDTO> searchColor(String name, Integer pageIndex, Integer pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        if (name == null){
            return getAllColorDTO(pageable);
        }
        return searchByName(name, pageable);
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

    public Integer getTotalPages() {
        return totalPages;
    }
}
