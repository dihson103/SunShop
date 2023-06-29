package com.dinhson.sunshop.appAdmin.colorManagement;

import com.dinhson.sunshop.appProduct.colors.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/colors")
public class ColorManagementController {

    private final ColorService colorService;

    @GetMapping
    public String colorManagement(@RequestParam(required = false) String search, Model model){
        List<ColorDTO> colorDTOS = colorService.searchColor(search);

        model.addAttribute("colors", colorDTOS);
        model.addAttribute("search", search);
        return "admin-colors-management";
    }

    @PostMapping
    public String addColor(@RequestParam String colorName, RedirectAttributes redirectAttributes){
        colorService.createColor(colorName);

        redirectAttributes.addFlashAttribute("message", "Add color success!!!");
        return "redirect:/admin/colors";
    }

    @PostMapping("update")
    public String updateColor(@RequestParam String colorName,
                              @RequestParam Integer colorId,
                              RedirectAttributes redirectAttributes){
        String message = colorService.updateColor(colorId, colorName);

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/colors";
    }

}
